// Copyright 2016-present 650 Industries. All rights reserved.

#import <UMCore/UMUtilitiesInterface.h>
#import <UMCore/UMUtilities.h>

#import <EXPermissions/EXPermissions.h>

#if __has_include(<EXPermissions/EXAudioRecordingPermissionRequester.h>)
  #import <EXPermissions/EXAudioRecordingPermissionRequester.h>
#endif

#if __has_include(<EXPermissions/EXCalendarRequester.h>)
  #import <EXPermissions/EXCalendarRequester.h>
#endif

#if __has_include(<EXPermissions/EXCameraPermissionRequester.h>)
  #import <EXPermissions/EXCameraPermissionRequester.h>
#endif

#if __has_include(<EXPermissions/EXContactsRequester.h>)
  #import <EXPermissions/EXContactsRequester.h>
#endif

#if __has_include(<EXPermissions/EXLocationRequester.h>)
  #import <EXPermissions/EXLocationRequester.h>
#endif

#if __has_include(<EXPermissions/EXUserNotificationRequester.h>)
  #import <EXPermissions/EXUserNotificationRequester.h>
#endif

#if __has_include(<EXPermissions/EXRemindersRequester.h>)
  #import <EXPermissions/EXRemindersRequester.h>
#endif

#if __has_include(<EXPermissions/EXRemoteNotificationRequester.h>)
  #import <EXPermissions/EXRemoteNotificationRequester.h>
#endif

#if __has_include(<EXPermissions/EXCameraRollRequester.h>)
  #import <EXPermissions/EXCameraRollRequester.h>
#endif

#if __has_include(<EXPermissions/EXSystemBrightnessRequester.h>)
  #import <EXPermissions/EXSystemBrightnessRequester.h>
#endif


NSString * const EXPermissionExpiresNever = @"never";

@interface EXPermissions ()

@property (nonatomic, strong) NSDictionary<NSString *, id<EXPermissionRequester>> *requesters;
@property (nonatomic, strong) NSMutableArray *requests;
@property (nonatomic, weak) UMModuleRegistry *moduleRegistry;

@end

@implementation EXPermissions

UM_EXPORT_MODULE(ExpoPermissions);

+ (const NSArray<Protocol *> *)exportedInterfaces
{
  return @[@protocol(UMPermissionsInterface)];
}

+ (NSDictionary<NSString *, id<EXPermissionRequester>> *)defaultRequesters:(EXPermissions *)permissionsModule
{
  NSDictionary *requesters =  @{
        #if __has_include(<EXPermissions/EXAudioRecordingPermissionRequester.h>)
           @"audioRecording": [[EXAudioRecordingPermissionRequester alloc] initWithPermissionsModule:permissionsModule],
        #endif
        #if __has_include(<EXPermissions/EXCalendarRequester.h>)
           @"calendar": [[EXCalendarRequester alloc] initWithPermissionsModule:permissionsModule],
        #endif
        #if __has_include(<EXPermissions/EXCameraPermissionRequester.h>)
           @"camera": [[EXCameraPermissionRequester alloc] initWithPermissionsModule:permissionsModule],
        #endif
        #if __has_include(<EXPermissions/EXCameraRollRequester.h>)
           @"cameraRoll": [[EXCameraRollRequester alloc] initWithPermissionsModule:permissionsModule],
        #endif
        #if __has_include(<EXPermissions/EXContactsRequester.h>)
           @"contacts": [[EXContactsRequester alloc] initWithPermissionsModule:permissionsModule],
        #endif
        #if __has_include(<EXPermissions/EXLocationRequester.h>)
           @"location": [[EXLocationRequester alloc] initWithPermissionsModule:permissionsModule],
        #endif
        #if __has_include(<EXPermissions/EXRemoteNotificationRequester.h>)
           @"notifications": [[EXRemoteNotificationRequester alloc] initWithPermissionsModule:permissionsModule],
        #endif
        #if __has_include(<EXPermissions/EXRemindersRequester.h>)
           @"reminders": [[EXRemindersRequester alloc] initWithPermissionsModule:permissionsModule],
        #endif
        #if __has_include(<EXPermissions/EXUserNotificationRequester.h>)
           @"userFacingNotifications": [[EXUserNotificationRequester alloc] initWithPermissionsModule:permissionsModule],
        #endif
        #if __has_include(<EXPermissions/EXSystemBrightnessRequester.h>)
           @"systemBrightness": [[EXSystemBrightnessRequester alloc] initWithPermissionsModule:permissionsModule]
        #endif
           };
  
  return requesters;
}

- (instancetype)init
{
  return [self initWithRequesters:[EXPermissions defaultRequesters:self]];
}

- (instancetype)initWithRequesters:(NSDictionary <NSString *, Class> *)requesters
{
  if (self = [super init]) {
    _requests = [NSMutableArray array];
    _requesters = requesters;
  }
  return self;
}

- (void)setModuleRegistry:(UMModuleRegistry *)moduleRegistry
{
  _moduleRegistry = moduleRegistry;
}

# pragma mark - Exported methods

UM_EXPORT_METHOD_AS(getAsync,
                    getPermissionsWithTypes:(NSString *)permissionType
                    resolver:(UMPromiseResolveBlock)resolve
                    rejecter:(UMPromiseRejectBlock)reject)
{
  NSMutableDictionary *permission = [NSMutableDictionary dictionaryWithDictionary:[self getPermissionsForResource:permissionType]];
  // permission type not found - reject immediately
  if (permission == nil) {
    return reject(@"E_PERMISSIONS_UNKNOWN", [NSString stringWithFormat:@"Unrecognized permission: %@", permissionType], nil);
  }
  resolve(permission);
}

UM_EXPORT_METHOD_AS(askAsync,
                    askAsyncForPermission:(NSString *)permissionType
                    resolver:(UMPromiseResolveBlock)resolve
                    rejecter:(UMPromiseRejectBlock)reject)
{
  [self askForPermissionWithType:permissionType
                     withResults:resolve 
                     withRejecter:reject];
}

# pragma mark - permission requsters / getters

- (NSDictionary *)getPermissionsForResource:(NSString *)type
{
  id<EXPermissionRequester> requester = _requesters[type];
  if (requester) {
    return [requester permissions];
  }
  return nil;
}

// From UMPermissionsInterface - use to check permission from code
// shorthand method that checks both global and per-experience permission
- (BOOL)hasGrantedPermission:(NSString *)permissionType
{
  NSDictionary *permissions = [self getPermissionsForResource:permissionType];
  if (!permissions) {
    UMLogWarn(@"Permission with type '%@' not found.", permissionType);
    return false;
  }
  
  return [permissions[@"status"] isEqualToString:@"granted"];
}

- (void)askForPermissionWithType:(NSString *)permissionType
                       withResults:(void (^)(NSDictionary *results))onResults
                      withRejecter:(UMPromiseRejectBlock)reject
{
  NSMutableDictionary *permission = [[self getPermissionsForResource:permissionType] mutableCopy];
    
  // permission type not found - reject immediately
  if (permission == nil) {
    return reject(@"E_PERMISSIONS_UNKNOWN", [NSString stringWithFormat:@"Unrecognized permission: %@", permissionType], nil);
  }
    
  BOOL isGranted = [EXPermissions statusForPermissions:permission] == EXPermissionStatusGranted;
  permission[@"granted"] = @(isGranted);
    
  if (isGranted) {
    return onResults(permission);
  }
  
 
  [self askForGlobalPermission:permissionType
                   withResolver:onResults
                   withRejecter:reject];
}

// From UMPermissionsInterface
- (void)askForPermission:(NSString *)permissionType
              withResult:(void (^)(NSDictionary *))onResult
            withRejecter:(UMPromiseRejectBlock)reject
{
  void (^customResolver)(NSDictionary *) = ^(NSDictionary *permission) {
    NSMutableDictionary *result = [permission mutableCopy];
    BOOL isGranted = [EXPermissions statusForPermissions:permission] == EXPermissionStatusGranted;
    result[@"granted"] = @(isGranted);
    onResult(result);
  };
  
  return [self askForPermissionWithType:permissionType
                            withResults:customResolver
                           withRejecter:reject];
}

- (void)askForGlobalPermission:(NSString *)permissionType
                  withResolver:(void (^)(NSDictionary *))resolver
                  withRejecter:(UMPromiseRejectBlock)reject
{
  id<EXPermissionRequester> requester = [self getPermissionRequesterForType:permissionType];
  if (requester == nil) {
    // TODO: other types of permission requesters, e.g. facebook
    return reject(@"E_PERMISSIONS_UNSUPPORTED", [NSString stringWithFormat:@"Cannot request permission: %@", permissionType], nil);
  }
  
  [self.requests addObject:requester];
  [requester setDelegate:self];
  [requester requestPermissionsWithResolver:resolver rejecter:reject];
}

# pragma mark - helpers

+ (NSString *)permissionStringForStatus:(EXPermissionStatus)status
{
  switch (status) {
    case EXPermissionStatusGranted:
      return @"granted";
    case EXPermissionStatusDenied:
      return @"denied";
    default:
      return @"undetermined";
  }
}

+ (EXPermissionStatus)statusForPermissions:(NSDictionary *)permissions
{
  NSString *status = permissions[@"status"];
  if ([status isEqualToString:@"granted"]) {
    return EXPermissionStatusGranted;
  } else if ([status isEqualToString:@"denied"]) {
    return EXPermissionStatusDenied;
  } else {
    return EXPermissionStatusUndetermined;
  }
}

- (id<EXPermissionRequester>)getPermissionRequesterForType:(NSString *)type
{
  return _requesters[type];
}

- (void)permissionRequesterDidFinish:(NSObject<EXPermissionRequester> *)requester
{
  if ([_requests containsObject:requester]) {
    [_requests removeObject:requester];
  }
}

- (UMModuleRegistry *)getModuleRegistry {
  return _moduleRegistry;
}

@end
