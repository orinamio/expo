package com.raizlabs.android.dbflow.config;

import java.util.ArrayList;
import java.util.List;
public final class ExpoNotificationPendingDeliveries$Database extends BaseDatabaseDefinition {


  public ExpoNotificationPendingDeliveries$Database(DatabaseHolder holder) {
    // Writing for: ExpoNotificationPendingDeliveries
    holder.putDatabaseForTable(expo.modules.notifications.postoffice.pendingdeliveries.PendingUserInteraction.class, this);
    holder.putDatabaseForTable(expo.modules.notifications.postoffice.pendingdeliveries.PendingForegroundNotification.class, this);

    // Begin Migrations
    // End Migrations

    models.add(expo.modules.notifications.postoffice.pendingdeliveries.PendingUserInteraction.class);
    modelTableNames.put("PendingUserInteraction", expo.modules.notifications.postoffice.pendingdeliveries.PendingUserInteraction.class);
    modelAdapters.put(expo.modules.notifications.postoffice.pendingdeliveries.PendingUserInteraction.class, new expo.modules.notifications.postoffice.pendingdeliveries.PendingUserInteraction$Adapter());
    models.add(expo.modules.notifications.postoffice.pendingdeliveries.PendingForegroundNotification.class);
    modelTableNames.put("PendingForegroundNotification", expo.modules.notifications.postoffice.pendingdeliveries.PendingForegroundNotification.class);
    modelAdapters.put(expo.modules.notifications.postoffice.pendingdeliveries.PendingForegroundNotification.class, new expo.modules.notifications.postoffice.pendingdeliveries.PendingForegroundNotification$Adapter());
    // Writing Query Models
  }

  @Override
  public final boolean isForeignKeysSupported() {
    return false;
  }

  @Override
  public final boolean backupEnabled() {
    return false;
  }

  @Override
  public final boolean areConsistencyChecksEnabled() {
    return false;
  }

  @Override
  public final int getDatabaseVersion() {
    return 1;
  }

  @Override
  public final String getDatabaseName() {
    return "ExpoNotificationPendingDeliveries";
  }
}
