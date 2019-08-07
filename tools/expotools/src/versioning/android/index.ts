import path from 'path';
import fs from 'fs-extra';
import chalk from 'chalk';
import semver from 'semver';

import { getExpoRepositoryRootDir } from '../../Directories';

const EXPO_DIR = getExpoRepositoryRootDir();

function getProjectPaths(abiName: string) {
  const androidPath = path.join(EXPO_DIR, 'android');
  const versionedExpoviewAbiPath = path.join(androidPath, 'versioned-abis', `expoview-${abiName}`);
  const expoviewBuildGradlePath = path.join(androidPath, 'expoview', 'build.gradle');
  const templateManifestPath = path.join(EXPO_DIR, 'template-files', 'android', 'AndroidManifest.xml');
  const settingsGradlePath = path.join(androidPath, 'settings.gradle');
  const appBuildGradlePath = path.join(androidPath, 'app', 'build.gradle');
  const buildGradlePath = path.join(androidPath, 'build.gradle');
  const sdkVersionsPath = path.join(androidPath, 'sdkVersions.json');
  const rnActivityPath = path.join(androidPath, 'expoview/src/main/java/host/exp/exponent/experience/MultipleVersionReactNativeActivity.java');
  const expoviewConstantsPath = path.join(androidPath, 'expoview/src/main/java/host/exp/exponent/Constants.java');

  return {
    versionedExpoviewAbiPath,
    expoviewBuildGradlePath,
    templateManifestPath,
    settingsGradlePath,
    appBuildGradlePath,
    buildGradlePath,
    sdkVersionsPath,
    rnActivityPath,
    expoviewConstantsPath,
  };
}

async function transformFileAsync(filePath: string, regexp: RegExp, replacement: string = '') {
  const fileContent = await fs.readFile(filePath, 'utf8');
  await fs.writeFile(filePath, fileContent.replace(regexp, replacement));
}

async function removeVersionReferencesFromFileAsync(sdkMajorVersion: string, filePath: string) {
  console.log(
    `Removing code surrounded by ${chalk.gray(`// BEGIN_SDK_${sdkMajorVersion}`)} and ${chalk.gray(`// END_SDK_${sdkMajorVersion}`)} from ${chalk.magenta(path.relative(EXPO_DIR, filePath))}...`
  );
  await transformFileAsync(
    filePath,
    new RegExp(`\\s*//\\s*BEGIN_SDK_${sdkMajorVersion}\\n.*?//\\s*END_SDK_${sdkMajorVersion}`, 'gs'),
    '',
  );
}

async function removeVersionedExpoviewAsync(versionedExpoviewAbiPath: string) {
  console.log(`Removing versioned expoview at ${chalk.magenta(path.relative(EXPO_DIR, versionedExpoviewAbiPath))}...`);
  await fs.remove(versionedExpoviewAbiPath);
}

async function removeFromTemplateManifestAsync(sdkMajorVersion: string, templateManifestPath: string) {
  console.log(
    `Removing code surrounded by ${chalk.gray(`<!-- BEGIN_SDK_${sdkMajorVersion} -->`)} and ${chalk.gray(`<!-- END_SDK_${sdkMajorVersion} -->`)} from ${chalk.magenta(path.relative(EXPO_DIR, templateManifestPath))}...`
  );
  await transformFileAsync(
    templateManifestPath,
    new RegExp(`\\s*<!--\\s*BEGIN_SDK_${sdkMajorVersion}\\s*-->.*?<!--\\s*END_SDK_${sdkMajorVersion}\\s*-->`, 'gs'),
    '',
  );
}

async function removeFromSettingsGradleAsync(abiName: string, settingsGradlePath: string) {
  console.log(
    `Removing ${chalk.green(`expoview-${abiName}`)} from ${chalk.magenta(path.relative(EXPO_DIR, settingsGradlePath))}...`
  );
  await transformFileAsync(
    settingsGradlePath,
    new RegExp(`\\n\\s*"${abiName}",[^\\n]*`, 'g'),
    '',
  );
}

async function removeFromBuildGradleAsync(abiName: string, buildGradlePath: string) {
  console.log(
    `Removing maven repository for ${chalk.green(`expoview-${abiName}`)} from ${chalk.magenta(path.relative(EXPO_DIR, buildGradlePath))}...`
  );
  await transformFileAsync(
    buildGradlePath,
    new RegExp(`\\s*maven\\s*{\\s*url\\s*"$rootDir/versioned-abis/expoview-${abiName}/maven"\\s*}\\s*`),
    '',
  );
}

async function removeFromSdkVersionsAsync(version: string, sdkVersionsPath: string) {
  console.log(
    `Removing ${chalk.cyan(version)} from ${chalk.magenta(path.relative(EXPO_DIR, sdkVersionsPath))}...`
  );
  await transformFileAsync(
    sdkVersionsPath,
    new RegExp(`"${version}",\s*`, 'g'),
    '',
  );
}

export async function removeVersionAsync(version: string) {
  const abiName = `abi${version.replace(/\./g, '_')}`;
  const paths = getProjectPaths(abiName);
  const sdkMajorVersion = semver.major(version);

  console.log(`Removing SDK version ${chalk.cyan(version)} for ${chalk.blue('Android')}...`);

  // Remove expoview-abi*_0_0 library
  await removeVersionedExpoviewAsync(paths.versionedExpoviewAbiPath);
  await removeFromSettingsGradleAsync(abiName, paths.settingsGradlePath);
  await removeFromBuildGradleAsync(abiName, paths.buildGradlePath);
  
  // Remove code surrounded by BEGIN_SDK_* and END_SDK_*
  await removeVersionReferencesFromFileAsync(sdkMajorVersion, paths.expoviewBuildGradlePath);
  await removeVersionReferencesFromFileAsync(sdkMajorVersion, paths.appBuildGradlePath);
  await removeVersionReferencesFromFileAsync(sdkMajorVersion, paths.rnActivityPath);
  await removeVersionReferencesFromFileAsync(sdkMajorVersion, paths.expoviewConstantsPath);

  // Remove test-suite tests
  // @Ignore
  // @Test
  // @ExpoTestSuiteTest
  // @ExpoSdkVersionTest("31.0.0")
  // public void sdk31TestSuite() {
  //   runTestSuiteTest("exp://exp.host/@exponent_ci_bot/test-suite-sdk-31-0-0", false);
  // }

  // Update AndroidManifest.xml in template-files
  await removeFromTemplateManifestAsync(sdkMajorVersion, paths.templateManifestPath);

  // Remove SDK version from the list of supported SDKs
  await removeFromSdkVersionsAsync(version, paths.sdkVersionsPath);
}
