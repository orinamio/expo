package expo.modules.notifications.provider;

import android.content.Context;

import org.unimodules.core.ModuleRegistry;
import org.unimodules.core.interfaces.InternalModule;

import java.util.Collections;
import java.util.List;

import expo.modules.notifications.configuration.Configuration;

public class BareAppIdProvider implements InternalModule, AppIdProvider{

    public BareAppIdProvider(Context context) { }

    public BareAppIdProvider() {}

    public String getAppId() {
        return Configuration.APP_ID;
    }

    @Override
    public List<? extends Class> getExportedInterfaces() {
        return Collections.singletonList(AppIdProvider.class);
    }

}
