package com.raizlabs.android.dbflow.config;

import java.util.ArrayList;
import java.util.List;
public final class ExpoScheduledNotifications$Database extends BaseDatabaseDefinition {


  public ExpoScheduledNotifications$Database(DatabaseHolder holder) {
    // Writing for: ExpoScheduledNotifications
    holder.putDatabaseForTable(expo.modules.notifications.insecurescheduler.repository.ScheduledNotification.class, this);

    // Begin Migrations
    // End Migrations

    models.add(expo.modules.notifications.insecurescheduler.repository.ScheduledNotification.class);
    modelTableNames.put("ScheduledNotification", expo.modules.notifications.insecurescheduler.repository.ScheduledNotification.class);
    modelAdapters.put(expo.modules.notifications.insecurescheduler.repository.ScheduledNotification.class, new expo.modules.notifications.insecurescheduler.repository.ScheduledNotification$Adapter());
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
    return "ExpoScheduledNotifications";
  }
}
