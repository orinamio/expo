package expo.modules.notifications.presenters.modifiers;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import static expo.modules.notifications.NotificationConstants.NOTIFICATION_VIBRATE;

public class VibrateModifier implements  NotificationModifier {
  @Override
  public void modify(NotificationCompat.Builder builder, Bundle notification, Context context, String experienceID) {
    if (notification.containsKey(NOTIFICATION_VIBRATE)) {
      builder.setVibrate(notification.getLongArray(NOTIFICATION_VIBRATE));
    }
  }
}
