package expo.modules.notifications.push;

import java.util.HashMap;
import java.util.Map;

import expo.modules.notifications.configuration.Configuration;
import expo.modules.notifications.push.engines.BareEngine;
import expo.modules.notifications.push.engines.ExpoEngine;
import expo.modules.notifications.push.engines.StabEngine;

public class PushNotificationEngineProvider {

    private static Map<String, Engine> engines;

    public synchronized static Engine getPushNotificationEngine() {
        init();
        return engines.get(Configuration.PUSH_NOTIFICATION_ENGINE);
    }

    private static void init() {
        if (engines == null) {
            engines = new HashMap<>();
            engines.put("none", new StabEngine());
            engines.put("bare", new BareEngine());
            engines.put("expo", new ExpoEngine());
        }
    }

}
