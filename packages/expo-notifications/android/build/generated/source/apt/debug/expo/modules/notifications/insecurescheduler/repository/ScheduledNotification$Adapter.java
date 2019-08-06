package expo.modules.notifications.insecurescheduler.repository;

import android.content.ContentValues;
import android.database.Cursor;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.SqlUtils;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.builder.ConditionQueryBuilder;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
// This table belongs to the ExpoScheduledNotifications database
public final class ScheduledNotification$Adapter extends ModelAdapter<ScheduledNotification> {

  @Override
  public Class<ScheduledNotification> getModelClass() {
    return ScheduledNotification.class;
  }

  @Override
  public String getTableName() {
    return expo.modules.notifications.insecurescheduler.repository.ScheduledNotification$Table.TABLE_NAME;
  }

  @Override
  protected final String getInsertStatementQuery() {
    return "INSERT INTO `ScheduledNotification` (`APPID`, `NOTIFICATIONID`) VALUES (?, ?)";
  }

  @Override
  public void bindToStatement(android.database.sqlite.SQLiteStatement statement, ScheduledNotification model) {
    if (((java.lang.String)model.appId) != null)  {
      statement.bindString(1,((java.lang.String)model.appId));
    } else {
      statement.bindNull(1);
    }
    statement.bindLong(2,((int)model.notificationId));

  }

  @Override
  public void bindToContentValues(ContentValues contentValues, ScheduledNotification model) {
    if (((java.lang.String)model.appId) != null)  {
      contentValues.put("appId",((java.lang.String)model.appId));
    } else {
      contentValues.putNull("appId");
    }
    contentValues.put("notificationId",((int)model.notificationId));

  }

  @Override
  public void bindToInsertValues(ContentValues contentValues, ScheduledNotification model) {
    if (((java.lang.String)model.appId) != null)  {
      contentValues.put("appId",((java.lang.String)model.appId));
    } else {
      contentValues.putNull("appId");
    }
    contentValues.put("notificationId",((int)model.notificationId));

  }

  @Override
  public boolean exists(ScheduledNotification model) {
    return new Select().from(ScheduledNotification.class).where(getPrimaryModelWhere(model)).hasData();
  }

  @Override
  public void loadFromCursor(Cursor cursor, ScheduledNotification model) {
    int indexappId = cursor.getColumnIndex("appId");
    if (indexappId != -1)  {
      if (cursor.isNull(indexappId)) {
        model.appId = null;
      } else {
        model.appId = cursor.getString(indexappId);
      }
    }
    int indexnotificationId = cursor.getColumnIndex("notificationId");
    if (indexnotificationId != -1)  {
      model.notificationId = cursor.getInt(indexnotificationId);
    }
  }

  @Override
  public ConditionQueryBuilder<ScheduledNotification> getPrimaryModelWhere(ScheduledNotification model) {
    return new ConditionQueryBuilder<ScheduledNotification>(ScheduledNotification.class, Condition.column(ScheduledNotification$Table.APPID).is((model.appId)),Condition.column(ScheduledNotification$Table.NOTIFICATIONID).is((model.notificationId)));
  }

  @Override
  public ConditionQueryBuilder<ScheduledNotification> createPrimaryModelWhere() {
    return new ConditionQueryBuilder<ScheduledNotification>(ScheduledNotification.class, Condition.column(ScheduledNotification$Table.APPID).is("?"),Condition.column(ScheduledNotification$Table.NOTIFICATIONID).is("?"));
  }

  @Override
  public String getCreationQuery() {
    return "CREATE TABLE IF NOT EXISTS `ScheduledNotification`(`appId` TEXT, `notificationId` INTEGER, PRIMARY KEY(`appId`, `notificationId`));";
  }

  @Override
  public final ScheduledNotification newInstance() {
    return new expo.modules.notifications.insecurescheduler.repository.ScheduledNotification();
  }
}
