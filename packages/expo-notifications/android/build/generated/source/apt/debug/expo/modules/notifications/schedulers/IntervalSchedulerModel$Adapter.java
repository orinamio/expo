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
public final class IntervalSchedulerModel$Adapter extends ModelAdapter<IntervalSchedulerModel> {

  @Override
  public Class<IntervalSchedulerModel> getModelClass() {
    return IntervalSchedulerModel.class;
  }

  @Override
  public String getTableName() {
    return expo.modules.notifications.schedulers.IntervalSchedulerModel$Table.TABLE_NAME;
  }

  @Override
  protected final String getInsertStatementQuery() {
    return "INSERT INTO `IntervalSchedulerModel` (`ID`, `APPID`, `REPEAT`, `SERIALIZEDDETAILS`, `SCHEDULEDTIME`, `INTERVAL`) VALUES (?, ?, ?, ?, ?, ?)";
  }

  @Override
  public void bindToStatement(android.database.sqlite.SQLiteStatement statement, IntervalSchedulerModel model) {
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
    statement.bindLong(5,((long)model.scheduledTime));
    statement.bindLong(6,((long)model.interval));

  }

  @Override
  public void bindToContentValues(ContentValues contentValues, IntervalSchedulerModel model) {
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
    contentValues.put("scheduledTime",((long)model.scheduledTime));
    contentValues.put("interval",((long)model.interval));

  }

  @Override
  public void bindToInsertValues(ContentValues contentValues, IntervalSchedulerModel model) {
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
    contentValues.put("scheduledTime",((long)model.scheduledTime));
    contentValues.put("interval",((long)model.interval));

  }

  @Override
  public boolean exists(IntervalSchedulerModel model) {
    return new Select().from(IntervalSchedulerModel.class).where(getPrimaryModelWhere(model)).hasData();
  }

  @Override
  public void loadFromCursor(Cursor cursor, IntervalSchedulerModel model) {
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
    int indexscheduledTime = cursor.getColumnIndex("scheduledTime");
    if (indexscheduledTime != -1)  {
      model.scheduledTime = cursor.getLong(indexscheduledTime);
    }
    int indexinterval = cursor.getColumnIndex("interval");
    if (indexinterval != -1)  {
      model.interval = cursor.getLong(indexinterval);
    }
  }

  @Override
  public boolean hasCachingId() {
    return true;
  }

  @Override
  public Object getCachingId(IntervalSchedulerModel model) {
    return model.id;
  }

  @Override
  public String getCachingColumnName() {
    return IntervalSchedulerModel$Table.ID;
  }

  @Override
  public Object getCachingIdFromCursorIndex(Cursor cursor, int indexid) {
    return cursor.getInt(indexid);
  }

  @Override
  public ConditionQueryBuilder<IntervalSchedulerModel> getPrimaryModelWhere(IntervalSchedulerModel model) {
    return new ConditionQueryBuilder<IntervalSchedulerModel>(IntervalSchedulerModel.class, Condition.column(IntervalSchedulerModel$Table.ID).is((model.id)));
  }

  @Override
  public ConditionQueryBuilder<IntervalSchedulerModel> createPrimaryModelWhere() {
    return new ConditionQueryBuilder<IntervalSchedulerModel>(IntervalSchedulerModel.class, Condition.column(IntervalSchedulerModel$Table.ID).is("?"));
  }

  @Override
  public String getCreationQuery() {
    return "CREATE TABLE IF NOT EXISTS `IntervalSchedulerModel`(`id` INTEGER, `appId` TEXT, `repeat` INTEGER, `serializedDetails` TEXT, `scheduledTime` INTEGER, `interval` INTEGER, PRIMARY KEY(`id`));";
  }

  @Override
  public final IntervalSchedulerModel newInstance() {
    return new expo.modules.notifications.schedulers.IntervalSchedulerModel();
  }
}
