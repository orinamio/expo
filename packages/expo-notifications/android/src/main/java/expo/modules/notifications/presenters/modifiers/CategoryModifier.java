package expo.modules.notifications.presenters.modifiers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import expo.modules.notifications.Constants;
import expo.modules.notifications.kernel.KernelConstants;
import expo.modules.notifications.action.IntentProvider;
import expo.modules.notifications.action.NotificationActionCenter;

import static expo.modules.notifications.NotificationConstants.NOTIFICATION_CATEGORY;

public class CategoryModifier implements NotificationModifier {
  @Override
  public void modify(NotificationCompat.Builder builder, Bundle notification, Context context, String appId) {
    if (notification.containsKey(NOTIFICATION_CATEGORY)) {
      String categoryId = getScopedIdIfNotDetached(notification.getString(NOTIFICATION_CATEGORY), appId);

      NotificationActionCenter.setCategory(categoryId, builder, context, new IntentProvider() {
        @Override
        public Intent provide() {
          Class activityClass = KernelConstants.MAIN_ACTIVITY_CLASS;
          Intent intent = new Intent(context, activityClass);
          intent.putExtra(KernelConstants.NOTIFICATION_OBJECT_KEY, notification);
          return intent;
        }
      });
    }
  }

  private String getScopedIdIfNotDetached(String categoryId, String appId) {
    if (!Constants.isStandaloneApp()) {
      return appId + ":" + categoryId;
    }
    return categoryId;
  }
}
