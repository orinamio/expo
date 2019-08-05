package expo.modules.notifications.insecurescheduler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.HashMap;

import android.os.Bundle;
import expo.modules.notifications.managers.SchedulersManagerProxy;
import expo.modules.notifications.helpers.Utils;
import expo.modules.notifications.presenters.NotificationPresenterProvider;

import static expo.modules.notifications.NotificationConstants.NOTIFICATION_EXPERIENCE_ID_KEY;
import static expo.modules.notifications.NotificationConstants.NOTIFICATION_ID_KEY;
import static expo.modules.notifications.NotificationConstants.NOTIFICATION_OBJECT_KEY;

public class ScheduledNotificationReceiver extends BroadcastReceiver {

  public void onReceive(Context context, Intent intent) {
    Bundle bundle = intent.getExtras();
    HashMap details = (HashMap) bundle.getSerializable(NOTIFICATION_OBJECT_KEY);
    int notificationId = bundle.getInt(NOTIFICATION_ID_KEY, 0);
    String schedulerId = (String) details.get(SchedulersManagerProxy.SCHEDULER_ID);

    ThreadSafeInsecureScheduler.getInstance().cancelScheduled(NOTIFICATION_EXPERIENCE_ID_KEY, notificationId, context);

    SchedulersManagerProxy.getInstance(context).rescheduleOrDelete(schedulerId);

    Bundle notification = Utils.convertMapToBundle(details).getBundle("data");

    NotificationPresenterProvider.getNotificationPresenter().presentNotification(
        context.getApplicationContext(),
        notification.getString("experienceId"),
        notification,
        notificationId
    );
  }
}
