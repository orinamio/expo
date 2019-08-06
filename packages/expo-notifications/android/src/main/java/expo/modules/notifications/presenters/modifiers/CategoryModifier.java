package expo.modules.notifications.presenters.modifiers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import expo.modules.notifications.action.IntentProvider;
import expo.modules.notifications.action.NotificationActionCenter;

import static expo.modules.notifications.NotificationConstants.NOTIFICATION_CATEGORY;
import static expo.modules.notifications.NotificationConstants.NOTIFICATION_OBJECT_KEY;
import static expo.modules.notifications.configuration.Configuration.MAIN_ACTIVITY_NAME;

public class CategoryModifier implements NotificationModifier {
  @Override
  public void modify(NotificationCompat.Builder builder, Bundle notification, Context context, String appId) {
    if (notification.containsKey(NOTIFICATION_CATEGORY)) {
      String categoryId = notification.getString(NOTIFICATION_CATEGORY);

      NotificationActionCenter.setCategory(categoryId, builder, context, new IntentProvider() {
        @Override
        public Intent provide() {
          Class activityClass = null;
          try {
            activityClass = Class.forName(MAIN_ACTIVITY_NAME);
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }
          Intent intent = new Intent(context, activityClass);
          intent.putExtra(NOTIFICATION_OBJECT_KEY, notification);
          return intent;
        }
      });
    }
  }
}
