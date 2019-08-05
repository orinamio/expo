// Copyright 2015-present 650 Industries. All rights reserved.

package expo.modules.notifications;

public class ReceivedNotificationEvent extends ExponentNotification {
  public ReceivedNotificationEvent(String appId, String body, int notificationId, boolean isMultiple, boolean isRemote) {
    super(appId, body, notificationId, isMultiple, isRemote);
  }
}
