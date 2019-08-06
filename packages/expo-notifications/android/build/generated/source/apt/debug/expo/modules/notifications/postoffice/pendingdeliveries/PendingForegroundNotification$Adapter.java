package expo.modules.notifications.postoffice.pendingdeliveries;

import android.content.ContentValues;
import android.database.Cursor;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.SqlUtils;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.builder.ConditionQueryBuilder;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
// This table belongs to the ExpoNotificationPendingDeliveries database
public final class PendingForegroundNotification$Adapter extends ModelAdapter<PendingForegroundNotification> {

  @Override
  public Class<PendingForegroundNotification> getModelClass() {
    return PendingForegroundNotification.class;
  }

  @Override
  public String getTableName() {
    return expo.modules.notifications.postoffice.pendingdeliveries.PendingForegroundNotification$Table.TABLE_NAME;
  }

  @Override
  protected final String getInsertStatementQuery() {
    return "INSERT INTO `PendingForegroundNotification` (`APPID`, `NOTIFICATION`) VALUES (?, ?)";
  }

  @Override
  public void bindToStatement(android.database.sqlite.SQLiteStatement statement, PendingForegroundNotification model) {
    if (((java.lang.String)model.appId) != null)  {
      statement.bindString(1,((java.lang.String)model.appId));
    } else {
      statement.bindNull(1);
    }
    if (((java.lang.String)model.notification) != null)  {
      statement.bindString(2,((java.lang.String)model.notification));
    } else {
      statement.bindNull(2);
    }

  }

  @Override
  public void bindToContentValues(ContentValues contentValues, PendingForegroundNotification model) {
    contentValues.put("id",((int)model.id));
    if (((java.lang.String)model.appId) != null)  {
      contentValues.put("appId",((java.lang.String)model.appId));
    } else {
      contentValues.putNull("appId");
    }
    if (((java.lang.String)model.notification) != null)  {
      contentValues.put("notification",((java.lang.String)model.notification));
    } else {
      contentValues.putNull("notification");
    }

  }

  @Override
  public void bindToInsertValues(ContentValues contentValues, PendingForegroundNotification model) {
    if (((java.lang.String)model.appId) != null)  {
      contentValues.put("appId",((java.lang.String)model.appId));
    } else {
      contentValues.putNull("appId");
    }
    if (((java.lang.String)model.notification) != null)  {
      contentValues.put("notification",((java.lang.String)model.notification));
    } else {
      contentValues.putNull("notification");
    }

  }

  @Override
  public boolean exists(PendingForegroundNotification model) {
    return  ((int)model.id) > 0;
  }

  @Override
  public void loadFromCursor(Cursor cursor, PendingForegroundNotification model) {
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
    int indexnotification = cursor.getColumnIndex("notification");
    if (indexnotification != -1)  {
      if (cursor.isNull(indexnotification)) {
        model.notification = null;
      } else {
        model.notification = cursor.getString(indexnotification);
      }
    }
  }

  @Override
  public void updateAutoIncrement(PendingForegroundNotification model, long id) {
    model.id = ((int)id);
  }

  @Override
  public long getAutoIncrementingId(PendingForegroundNotification model) {
    return ((long)model.id);
  }

  @Override
  public String getAutoIncrementingColumnName() {
    return PendingForegroundNotification$Table.ID;
  }

  @Override
  public ConditionQueryBuilder<PendingForegroundNotification> getPrimaryModelWhere(PendingForegroundNotification model) {
    return new ConditionQueryBuilder<PendingForegroundNotification>(PendingForegroundNotification.class, Condition.column(PendingForegroundNotification$Table.ID).is((model.id)));
  }

  @Override
  public ConditionQueryBuilder<PendingForegroundNotification> createPrimaryModelWhere() {
    return new ConditionQueryBuilder<PendingForegroundNotification>(PendingForegroundNotification.class, Condition.column(PendingForegroundNotification$Table.ID).is("?"));
  }

  @Override
  public String getCreationQuery() {
    return "CREATE TABLE IF NOT EXISTS `PendingForegroundNotification`(`id` INTEGER PRIMARY KEY AUTOINCREMENT, `appId` TEXT, `notification` TEXT);";
  }

  @Override
  public final PendingForegroundNotification newInstance() {
    return new expo.modules.notifications.postoffice.pendingdeliveries.PendingForegroundNotification();
  }
}
