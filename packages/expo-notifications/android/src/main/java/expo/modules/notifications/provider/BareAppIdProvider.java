package expo.modules.notifications.provider;

import android.content.Context;

import org.unimodules.core.ModuleRegistry;
import org.unimodules.core.interfaces.InternalModule;

import java.util.Collections;
import java.util.List;

import expo.modules.notifications.configuration.Configuration;

public class BareAppIdProvider implements InternalModule, AppIdProvider{

    private static String DEFAULT_APP_ID = "defaultId";

    public BareAppIdProvider(Context context) { }

    public BareAppIdProvider() {}

    public String getAppId() {
        if (!Configuration.APP_ID.equals("")) {
            return Configuration.APP_ID;
        }

        return DEFAULT_APP_ID;
    }

    @Override
    public List<? extends Class> getExportedInterfaces() {
        return Collections.singletonList(AppIdProvider.class);
    }

}
