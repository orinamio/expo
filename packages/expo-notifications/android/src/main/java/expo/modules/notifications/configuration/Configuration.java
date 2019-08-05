package expo.modules.notifications.configuration;

public class Configuration {

    public static String APP_ID = "";

    public static String PUSH_NOTIFICATION_ENGINE = "bare"; // possible: bare, expo, none
    /*
        if 'none' value is set then add the following meta data to AndroidManifest.xml

        <meta-data
            android:name="firebase_messaging_auto_init_enabled"
            android:value="false"
        />
     */

    public static String MAIN_ACTIVITY_NAME = "com.notifications_test.MainActivity.class";

}
