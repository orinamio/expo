package expo.modules.notifications.channels;

import android.content.ContentValues;
import android.database.Cursor;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.SqlUtils;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.builder.ConditionQueryBuilder;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
// This table belongs to the ExpoNotificationActions database
public final class ChannelProperties$Adapter extends ModelAdapter<ChannelProperties> {

  @Override
  public Class<ChannelProperties> getModelClass() {
    return ChannelProperties.class;
  }

  @Override
  public String getTableName() {
    return expo.modules.notifications.channels.ChannelProperties$Table.TABLE_NAME;
  }

  @Override
  protected final String getInsertStatementQuery() {
    return "INSERT INTO `ChannelProperties` (`CHANNELID`, `SERIALIZEDCHANNEL`) VALUES (?, ?)";
  }

  @Override
  public void bindToStatement(android.database.sqlite.SQLiteStatement statement, ChannelProperties model) {
    if (((java.lang.String)model.channelId) != null)  {
      statement.bindString(1,((java.lang.String)model.channelId));
    } else {
      statement.bindNull(1);
    }
    if (((java.lang.String)model.serializedChannel) != null)  {
      statement.bindString(2,((java.lang.String)model.serializedChannel));
    } else {
      statement.bindNull(2);
    }

  }

  @Override
  public void bindToContentValues(ContentValues contentValues, ChannelProperties model) {
    if (((java.lang.String)model.channelId) != null)  {
      contentValues.put("channelId",((java.lang.String)model.channelId));
    } else {
      contentValues.putNull("channelId");
    }
    if (((java.lang.String)model.serializedChannel) != null)  {
      contentValues.put("serializedChannel",((java.lang.String)model.serializedChannel));
    } else {
      contentValues.putNull("serializedChannel");
    }

  }

  @Override
  public void bindToInsertValues(ContentValues contentValues, ChannelProperties model) {
    if (((java.lang.String)model.channelId) != null)  {
      contentValues.put("channelId",((java.lang.String)model.channelId));
    } else {
      contentValues.putNull("channelId");
    }
    if (((java.lang.String)model.serializedChannel) != null)  {
      contentValues.put("serializedChannel",((java.lang.String)model.serializedChannel));
    } else {
      contentValues.putNull("serializedChannel");
    }

  }

  @Override
  public boolean exists(ChannelProperties model) {
    return new Select().from(ChannelProperties.class).where(getPrimaryModelWhere(model)).hasData();
  }

  @Override
  public void loadFromCursor(Cursor cursor, ChannelProperties model) {
    int indexchannelId = cursor.getColumnIndex("channelId");
    if (indexchannelId != -1)  {
      if (cursor.isNull(indexchannelId)) {
        model.channelId = null;
      } else {
        model.channelId = cursor.getString(indexchannelId);
      }
    }
    int indexserializedChannel = cursor.getColumnIndex("serializedChannel");
    if (indexserializedChannel != -1)  {
      if (cursor.isNull(indexserializedChannel)) {
        model.serializedChannel = null;
      } else {
        model.serializedChannel = cursor.getString(indexserializedChannel);
      }
    }
  }

  @Override
  public boolean hasCachingId() {
    return true;
  }

  @Override
  public Object getCachingId(ChannelProperties model) {
    return model.channelId;
  }

  @Override
  public String getCachingColumnName() {
    return ChannelProperties$Table.CHANNELID;
  }

  @Override
  public Object getCachingIdFromCursorIndex(Cursor cursor, int indexchannelId) {
    return cursor.getString(indexchannelId);
  }

  @Override
  public ConditionQueryBuilder<ChannelProperties> getPrimaryModelWhere(ChannelProperties model) {
    return new ConditionQueryBuilder<ChannelProperties>(ChannelProperties.class, Condition.column(ChannelProperties$Table.CHANNELID).is((model.channelId)));
  }

  @Override
  public ConditionQueryBuilder<ChannelProperties> createPrimaryModelWhere() {
    return new ConditionQueryBuilder<ChannelProperties>(ChannelProperties.class, Condition.column(ChannelProperties$Table.CHANNELID).is("?"));
  }

  @Override
  public String getCreationQuery() {
    return "CREATE TABLE IF NOT EXISTS `ChannelProperties`(`channelId` TEXT, `serializedChannel` TEXT, PRIMARY KEY(`channelId`));";
  }

  @Override
  public final ChannelProperties newInstance() {
    return new expo.modules.notifications.channels.ChannelProperties();
  }
}
