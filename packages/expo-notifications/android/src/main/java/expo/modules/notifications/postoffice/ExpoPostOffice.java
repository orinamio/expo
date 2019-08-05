package expo.modules.notifications.postoffice;

import android.os.Bundle;

public interface ExpoPostOffice {

  void notifyAboutUserInteraction(String appId, Bundle userInteraction);

  void sendForegroundNotification(String appId, Bundle notification);

  void registerModuleAndGetPendingDeliveries(String appId, Mailbox mailbox);

  void unregisterModule(String appId);

}
