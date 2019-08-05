package expo.modules.notifications.insecurescheduler;

import android.content.Context;
import android.os.Bundle;

import java.util.HashMap;

public interface InsecureScheduler {

  void schedule(String appId, long elapsedTime, int notificationId, HashMap notification, final Context context);

  void cancelScheduled(String appId, int notificationId, final Context context);

  void cancelAllScheduled(String appId, final Context context);

}
