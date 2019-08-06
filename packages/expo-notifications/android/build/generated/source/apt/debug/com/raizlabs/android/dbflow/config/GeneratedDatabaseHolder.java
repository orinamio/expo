package com.raizlabs.android.dbflow.config;

public final class GeneratedDatabaseHolder extends DatabaseHolder {
  public GeneratedDatabaseHolder() {
    // Registering with FlowManagerHolder
    new com.raizlabs.android.dbflow.config.ExpoNotificationActions$Database(this);
    new com.raizlabs.android.dbflow.config.SchedulersDatabase$Database(this);
    new com.raizlabs.android.dbflow.config.ExpoNotificationPendingDeliveries$Database(this);
    new com.raizlabs.android.dbflow.config.ExpoScheduledNotifications$Database(this);

  }
  static {
    typeConverters.put(java.sql.Date.class, new com.raizlabs.android.dbflow.converter.SqlDateConverter());
    typeConverters.put(java.util.Calendar.class, new com.raizlabs.android.dbflow.converter.CalendarConverter());
    typeConverters.put(java.util.Date.class, new com.raizlabs.android.dbflow.converter.DateConverter());
    typeConverters.put(java.lang.Boolean.class, new com.raizlabs.android.dbflow.converter.BooleanConverter());
  }
}
