package expo.modules.notifications.schedulers;

import android.content.ContentValues;
import android.database.Cursor;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.SqlUtils;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.builder.ConditionQueryBuilder;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
// This table belongs to the SchedulersDatabase database
public final class CalendarSchedulerModel$Adapter extends ModelAdapter<CalendarSchedulerModel> {

  @Override
  public Class<CalendarSchedulerModel> getModelClass() {
    return CalendarSchedulerModel.class;
  }

  @Override
  public String getTableName() {
    return expo.modules.notifications.schedulers.CalendarSchedulerModel$Table.TABLE_NAME;
  }

  @Override
  protected final String getInsertStatementQuery() {
    return "INSERT INTO `CalendarSchedulerModel` (`ID`, `APPID`, `REPEAT`, `SERIALIZEDDETAILS`, `CALENDARDATA`) VALUES (?, ?, ?, ?, ?)";
  }

  @Override
  public void bindToStatement(android.database.sqlite.SQLiteStatement statement, CalendarSchedulerModel model) {
    statement.bindLong(1,((int)model.id));
    if (((java.lang.String)model.appId) != null)  {
      statement.bindString(2,((java.lang.String)model.appId));
    } else {
      statement.bindNull(2);
    }
    Object modelrepeat = FlowManager.getTypeConverterForClass(java.lang.Boolean.class).getDBValue(model.repeat);
    if (modelrepeat != null)  {
      statement.bindLong(3,((java.lang.Integer)modelrepeat));
    } else {
      statement.bindNull(3);
    }
    if (((java.lang.String)model.serializedDetails) != null)  {
      statement.bindString(4,((java.lang.String)model.serializedDetails));
    } else {
      statement.bindNull(4);
    }
    if (((java.lang.String)model.calendarData) != null)  {
      statement.bindString(5,((java.lang.String)model.calendarData));
    } else {
      statement.bindNull(5);
    }

  }

  @Override
  public void bindToContentValues(ContentValues contentValues, CalendarSchedulerModel model) {
    contentValues.put("id",((int)model.id));
    if (((java.lang.String)model.appId) != null)  {
      contentValues.put("appId",((java.lang.String)model.appId));
    } else {
      contentValues.putNull("appId");
    }
    Object modelrepeat = FlowManager.getTypeConverterForClass(java.lang.Boolean.class).getDBValue(model.repeat);
    if (modelrepeat != null)  {
      contentValues.put("repeat",((java.lang.Integer)modelrepeat));
    } else {
      contentValues.putNull("repeat");
    }
    if (((java.lang.String)model.serializedDetails) != null)  {
      contentValues.put("serializedDetails",((java.lang.String)model.serializedDetails));
    } else {
      contentValues.putNull("serializedDetails");
    }
    if (((java.lang.String)model.calendarData) != null)  {
      contentValues.put("calendarData",((java.lang.String)model.calendarData));
    } else {
      contentValues.putNull("calendarData");
    }

  }

  @Override
  public void bindToInsertValues(ContentValues contentValues, CalendarSchedulerModel model) {
    contentValues.put("id",((int)model.id));
    if (((java.lang.String)model.appId) != null)  {
      contentValues.put("appId",((java.lang.String)model.appId));
    } else {
      contentValues.putNull("appId");
    }
    Object modelrepeat = FlowManager.getTypeConverterForClass(java.lang.Boolean.class).getDBValue(model.repeat);
    if (modelrepeat != null)  {
      contentValues.put("repeat",((java.lang.Integer)modelrepeat));
    } else {
      contentValues.putNull("repeat");
    }
    if (((java.lang.String)model.serializedDetails) != null)  {
      contentValues.put("serializedDetails",((java.lang.String)model.serializedDetails));
    } else {
      contentValues.putNull("serializedDetails");
    }
    if (((java.lang.String)model.calendarData) != null)  {
      contentValues.put("calendarData",((java.lang.String)model.calendarData));
    } else {
      contentValues.putNull("calendarData");
    }

  }

  @Override
  public boolean exists(CalendarSchedulerModel model) {
    return new Select().from(CalendarSchedulerModel.class).where(getPrimaryModelWhere(model)).hasData();
  }

  @Override
  public void loadFromCursor(Cursor cursor, CalendarSchedulerModel model) {
    int indexid = cursor.getColumnIndex("id");
    if (indexid != -1)  {
      model.id = cursor.getInt(indexid);
    }
    int indexappId = cursor.getColumnIndex("appId");
    if (indexappId != -1)  {
      if (cursor.isNull(indexappId)) {
        model.appId = null;
      } else {
        model.appId = cursor.getString(indexappId);
      }
    }
    int indexrepeat = cursor.getColumnIndex("repeat");
    if (indexrepeat != -1)  {
      model.repeat = ((java.lang.Boolean)FlowManager.getTypeConverterForClass(java.lang.Boolean.class).getModelValue(cursor.getInt(indexrepeat)));
    }
    int indexserializedDetails = cursor.getColumnIndex("serializedDetails");
    if (indexserializedDetails != -1)  {
      if (cursor.isNull(indexserializedDetails)) {
        model.serializedDetails = null;
      } else {
        model.serializedDetails = cursor.getString(indexserializedDetails);
      }
    }
    int indexcalendarData = cursor.getColumnIndex("calendarData");
    if (indexcalendarData != -1)  {
      if (cursor.isNull(indexcalendarData)) {
        model.calendarData = null;
      } else {
        model.calendarData = cursor.getString(indexcalendarData);
      }
    }
  }

  @Override
  public boolean hasCachingId() {
    return true;
  }

  @Override
  public Object getCachingId(CalendarSchedulerModel model) {
    return model.id;
  }

  @Override
  public String getCachingColumnName() {
    return CalendarSchedulerModel$Table.ID;
  }

  @Override
  public Object getCachingIdFromCursorIndex(Cursor cursor, int indexid) {
    return cursor.getInt(indexid);
  }

  @Override
  public ConditionQueryBuilder<CalendarSchedulerModel> getPrimaryModelWhere(CalendarSchedulerModel model) {
    return new ConditionQueryBuilder<CalendarSchedulerModel>(CalendarSchedulerModel.class, Condition.column(CalendarSchedulerModel$Table.ID).is((model.id)));
  }

  @Override
  public ConditionQueryBuilder<CalendarSchedulerModel> createPrimaryModelWhere() {
    return new ConditionQueryBuilder<CalendarSchedulerModel>(CalendarSchedulerModel.class, Condition.column(CalendarSchedulerModel$Table.ID).is("?"));
  }

  @Override
  public String getCreationQuery() {
    return "CREATE TABLE IF NOT EXISTS `CalendarSchedulerModel`(`id` INTEGER, `appId` TEXT, `repeat` INTEGER, `serializedDetails` TEXT, `calendarData` TEXT, PRIMARY KEY(`id`));";
  }

  @Override
  public final CalendarSchedulerModel newInstance() {
    return new expo.modules.notifications.schedulers.CalendarSchedulerModel();
  }
}
