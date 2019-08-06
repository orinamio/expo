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
public final class PendingUserInteraction$Adapter extends ModelAdapter<PendingUserInteraction> {

  @Override
  public Class<PendingUserInteraction> getModelClass() {
    return PendingUserInteraction.class;
  }

  @Override
  public String getTableName() {
    return expo.modules.notifications.postoffice.pendingdeliveries.PendingUserInteraction$Table.TABLE_NAME;
  }

  @Override
  protected final String getInsertStatementQuery() {
    return "INSERT INTO `PendingUserInteraction` (`APPID`, `USERINTERACTION`) VALUES (?, ?)";
  }

  @Override
  public void bindToStatement(android.database.sqlite.SQLiteStatement statement, PendingUserInteraction model) {
    if (((java.lang.String)model.appId) != null)  {
      statement.bindString(1,((java.lang.String)model.appId));
    } else {
      statement.bindNull(1);
    }
    if (((java.lang.String)model.userInteraction) != null)  {
      statement.bindString(2,((java.lang.String)model.userInteraction));
    } else {
      statement.bindNull(2);
    }

  }

  @Override
  public void bindToContentValues(ContentValues contentValues, PendingUserInteraction model) {
    contentValues.put("id",((int)model.id));
    if (((java.lang.String)model.appId) != null)  {
      contentValues.put("appId",((java.lang.String)model.appId));
    } else {
      contentValues.putNull("appId");
    }
    if (((java.lang.String)model.userInteraction) != null)  {
      contentValues.put("userInteraction",((java.lang.String)model.userInteraction));
    } else {
      contentValues.putNull("userInteraction");
    }

  }

  @Override
  public void bindToInsertValues(ContentValues contentValues, PendingUserInteraction model) {
    if (((java.lang.String)model.appId) != null)  {
      contentValues.put("appId",((java.lang.String)model.appId));
    } else {
      contentValues.putNull("appId");
    }
    if (((java.lang.String)model.userInteraction) != null)  {
      contentValues.put("userInteraction",((java.lang.String)model.userInteraction));
    } else {
      contentValues.putNull("userInteraction");
    }

  }

  @Override
  public boolean exists(PendingUserInteraction model) {
    return  ((int)model.id) > 0;
  }

  @Override
  public void loadFromCursor(Cursor cursor, PendingUserInteraction model) {
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
    int indexuserInteraction = cursor.getColumnIndex("userInteraction");
    if (indexuserInteraction != -1)  {
      if (cursor.isNull(indexuserInteraction)) {
        model.userInteraction = null;
      } else {
        model.userInteraction = cursor.getString(indexuserInteraction);
      }
    }
  }

  @Override
  public void updateAutoIncrement(PendingUserInteraction model, long id) {
    model.id = ((int)id);
  }

  @Override
  public long getAutoIncrementingId(PendingUserInteraction model) {
    return ((long)model.id);
  }

  @Override
  public String getAutoIncrementingColumnName() {
    return PendingUserInteraction$Table.ID;
  }

  @Override
  public ConditionQueryBuilder<PendingUserInteraction> getPrimaryModelWhere(PendingUserInteraction model) {
    return new ConditionQueryBuilder<PendingUserInteraction>(PendingUserInteraction.class, Condition.column(PendingUserInteraction$Table.ID).is((model.id)));
  }

  @Override
  public ConditionQueryBuilder<PendingUserInteraction> createPrimaryModelWhere() {
    return new ConditionQueryBuilder<PendingUserInteraction>(PendingUserInteraction.class, Condition.column(PendingUserInteraction$Table.ID).is("?"));
  }

  @Override
  public String getCreationQuery() {
    return "CREATE TABLE IF NOT EXISTS `PendingUserInteraction`(`id` INTEGER PRIMARY KEY AUTOINCREMENT, `appId` TEXT, `userInteraction` TEXT);";
  }

  @Override
  public final PendingUserInteraction newInstance() {
    return new expo.modules.notifications.postoffice.pendingdeliveries.PendingUserInteraction();
  }
}
