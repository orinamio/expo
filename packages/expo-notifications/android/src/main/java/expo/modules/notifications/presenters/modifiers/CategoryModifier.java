package expo.modules.notifications.presenters.modifiers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import expo.modules.notifications.Constants;
import expo.modules.notifications.kernel.KernelConstants;
import expo.modules.notifications.IntentProvider;
import expo.modules.notifications.NotificationActionCenter;

import static expo.modules.notifications.NotificationConstants.NOTIFICATION_CATEGORY;

public class CategoryModifier implements NotificationModifier {
  @Override
  public void modify(NotificationCompat.Builder builder, Bundle notification, Context context, String experienceId) {
    if (notification.containsKey(NOTIFICATION_CATEGORY)) {
      String categoryId = getScopedIdIfNotDetached(notification.getString(NOTIFICATION_CATEGORY), experienceId);

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

  private String getScopedIdIfNotDetached(String categoryId, String experienceId) {
    if (!Constants.isStandaloneApp()) {
      return experienceId + ":" + categoryId;
    }
    return categoryId;
  }
}
