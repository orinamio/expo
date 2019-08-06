package com.raizlabs.android.dbflow.config;

import java.util.ArrayList;
import java.util.List;
public final class ExpoNotificationActions$Database extends BaseDatabaseDefinition {


  public ExpoNotificationActions$Database(DatabaseHolder holder) {
    // Writing for: ExpoNotificationActions
    holder.putDatabaseForTable(expo.modules.notifications.channels.ChannelProperties.class, this);
    holder.putDatabaseForTable(expo.modules.notifications.action.ActionObject.class, this);

    // Begin Migrations
    // End Migrations

    models.add(expo.modules.notifications.channels.ChannelProperties.class);
    modelTableNames.put("ChannelProperties", expo.modules.notifications.channels.ChannelProperties.class);
    modelAdapters.put(expo.modules.notifications.channels.ChannelProperties.class, new expo.modules.notifications.channels.ChannelProperties$Adapter());
    models.add(expo.modules.notifications.action.ActionObject.class);
    modelTableNames.put("ActionObject", expo.modules.notifications.action.ActionObject.class);
    modelAdapters.put(expo.modules.notifications.action.ActionObject.class, new expo.modules.notifications.action.ActionObject$Adapter());
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
    return "ExpoNotificationActions";
  }
}
