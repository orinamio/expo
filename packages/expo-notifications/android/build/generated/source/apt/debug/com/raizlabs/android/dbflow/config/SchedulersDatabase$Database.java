package com.raizlabs.android.dbflow.config;

import java.util.ArrayList;
import java.util.List;
public final class SchedulersDatabase$Database extends BaseDatabaseDefinition {


  public SchedulersDatabase$Database(DatabaseHolder holder) {
    // Writing for: SchedulersDatabase
    holder.putDatabaseForTable(expo.modules.notifications.schedulers.CalendarSchedulerModel.class, this);
    holder.putDatabaseForTable(expo.modules.notifications.schedulers.IntervalSchedulerModel.class, this);

    // Begin Migrations
    // End Migrations

    models.add(expo.modules.notifications.schedulers.CalendarSchedulerModel.class);
    modelTableNames.put("CalendarSchedulerModel", expo.modules.notifications.schedulers.CalendarSchedulerModel.class);
    modelAdapters.put(expo.modules.notifications.schedulers.CalendarSchedulerModel.class, new expo.modules.notifications.schedulers.CalendarSchedulerModel$Adapter());
    models.add(expo.modules.notifications.schedulers.IntervalSchedulerModel.class);
    modelTableNames.put("IntervalSchedulerModel", expo.modules.notifications.schedulers.IntervalSchedulerModel.class);
    modelAdapters.put(expo.modules.notifications.schedulers.IntervalSchedulerModel.class, new expo.modules.notifications.schedulers.IntervalSchedulerModel$Adapter());
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
    return "SchedulersDatabase";
  }
}
