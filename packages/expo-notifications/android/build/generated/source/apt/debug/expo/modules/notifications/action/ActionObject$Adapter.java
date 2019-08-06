package expo.modules.notifications.action;

import android.content.ContentValues;
import android.database.Cursor;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.SqlUtils;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.builder.ConditionQueryBuilder;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
// This table belongs to the ExpoNotificationActions database
public final class ActionObject$Adapter extends ModelAdapter<ActionObject> {

  @Override
  public Class<ActionObject> getModelClass() {
    return ActionObject.class;
  }

  @Override
  public String getTableName() {
    return expo.modules.notifications.action.ActionObject$Table.TABLE_NAME;
  }

  @Override
  protected final String getInsertStatementQuery() {
    return "INSERT INTO `ActionObject` (`CATEGORYID`, `ACTIONID`, `BUTTONTITLE`, `ISDESTRUCTIVE`, `ISAUTHENTICATIONREQUIRED`, `SUBMITBUTTONTITLE`, `PLACEHOLDER`, `SHOULDSHOWTEXTINPUT`, `POSITION`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
  }

  @Override
  public void bindToStatement(android.database.sqlite.SQLiteStatement statement, ActionObject model) {
    if (((java.lang.String)model.getCategoryId()) != null)  {
      statement.bindString(1,((java.lang.String)model.getCategoryId()));
    } else {
      statement.bindNull(1);
    }
    if (((java.lang.String)model.getActionId()) != null)  {
      statement.bindString(2,((java.lang.String)model.getActionId()));
    } else {
      statement.bindNull(2);
    }
    if (((java.lang.String)model.getButtonTitle()) != null)  {
      statement.bindString(3,((java.lang.String)model.getButtonTitle()));
    } else {
      statement.bindNull(3);
    }
    Object modelisDestructive = FlowManager.getTypeConverterForClass(java.lang.Boolean.class).getDBValue(model.getIsDestructive());
    if (modelisDestructive != null)  {
      statement.bindLong(4,((java.lang.Integer)modelisDestructive));
    } else {
      statement.bindNull(4);
    }
    Object modelisAuthenticationRequired = FlowManager.getTypeConverterForClass(java.lang.Boolean.class).getDBValue(model.getIsAuthenticationRequired());
    if (modelisAuthenticationRequired != null)  {
      statement.bindLong(5,((java.lang.Integer)modelisAuthenticationRequired));
    } else {
      statement.bindNull(5);
    }
    if (((java.lang.String)model.getSubmitButtonTitle()) != null)  {
      statement.bindString(6,((java.lang.String)model.getSubmitButtonTitle()));
    } else {
      statement.bindNull(6);
    }
    if (((java.lang.String)model.getPlaceholder()) != null)  {
      statement.bindString(7,((java.lang.String)model.getPlaceholder()));
    } else {
      statement.bindNull(7);
    }
    Object modelshouldShowTextInput = FlowManager.getTypeConverterForClass(java.lang.Boolean.class).getDBValue(model.getShouldShowTextInput());
    if (modelshouldShowTextInput != null)  {
      statement.bindLong(8,((java.lang.Integer)modelshouldShowTextInput));
    } else {
      statement.bindNull(8);
    }
    statement.bindLong(9,((int)model.getPosition()));

  }

  @Override
  public void bindToContentValues(ContentValues contentValues, ActionObject model) {
    if (((java.lang.String)model.getCategoryId()) != null)  {
      contentValues.put("categoryId",((java.lang.String)model.getCategoryId()));
    } else {
      contentValues.putNull("categoryId");
    }
    if (((java.lang.String)model.getActionId()) != null)  {
      contentValues.put("actionId",((java.lang.String)model.getActionId()));
    } else {
      contentValues.putNull("actionId");
    }
    if (((java.lang.String)model.getButtonTitle()) != null)  {
      contentValues.put("buttonTitle",((java.lang.String)model.getButtonTitle()));
    } else {
      contentValues.putNull("buttonTitle");
    }
    Object modelisDestructive = FlowManager.getTypeConverterForClass(java.lang.Boolean.class).getDBValue(model.getIsDestructive());
    if (modelisDestructive != null)  {
      contentValues.put("isDestructive",((java.lang.Integer)modelisDestructive));
    } else {
      contentValues.putNull("isDestructive");
    }
    Object modelisAuthenticationRequired = FlowManager.getTypeConverterForClass(java.lang.Boolean.class).getDBValue(model.getIsAuthenticationRequired());
    if (modelisAuthenticationRequired != null)  {
      contentValues.put("isAuthenticationRequired",((java.lang.Integer)modelisAuthenticationRequired));
    } else {
      contentValues.putNull("isAuthenticationRequired");
    }
    if (((java.lang.String)model.getSubmitButtonTitle()) != null)  {
      contentValues.put("submitButtonTitle",((java.lang.String)model.getSubmitButtonTitle()));
    } else {
      contentValues.putNull("submitButtonTitle");
    }
    if (((java.lang.String)model.getPlaceholder()) != null)  {
      contentValues.put("placeholder",((java.lang.String)model.getPlaceholder()));
    } else {
      contentValues.putNull("placeholder");
    }
    Object modelshouldShowTextInput = FlowManager.getTypeConverterForClass(java.lang.Boolean.class).getDBValue(model.getShouldShowTextInput());
    if (modelshouldShowTextInput != null)  {
      contentValues.put("shouldShowTextInput",((java.lang.Integer)modelshouldShowTextInput));
    } else {
      contentValues.putNull("shouldShowTextInput");
    }
    contentValues.put("position",((int)model.getPosition()));

  }

  @Override
  public void bindToInsertValues(ContentValues contentValues, ActionObject model) {
    if (((java.lang.String)model.getCategoryId()) != null)  {
      contentValues.put("categoryId",((java.lang.String)model.getCategoryId()));
    } else {
      contentValues.putNull("categoryId");
    }
    if (((java.lang.String)model.getActionId()) != null)  {
      contentValues.put("actionId",((java.lang.String)model.getActionId()));
    } else {
      contentValues.putNull("actionId");
    }
    if (((java.lang.String)model.getButtonTitle()) != null)  {
      contentValues.put("buttonTitle",((java.lang.String)model.getButtonTitle()));
    } else {
      contentValues.putNull("buttonTitle");
    }
    Object modelisDestructive = FlowManager.getTypeConverterForClass(java.lang.Boolean.class).getDBValue(model.getIsDestructive());
    if (modelisDestructive != null)  {
      contentValues.put("isDestructive",((java.lang.Integer)modelisDestructive));
    } else {
      contentValues.putNull("isDestructive");
    }
    Object modelisAuthenticationRequired = FlowManager.getTypeConverterForClass(java.lang.Boolean.class).getDBValue(model.getIsAuthenticationRequired());
    if (modelisAuthenticationRequired != null)  {
      contentValues.put("isAuthenticationRequired",((java.lang.Integer)modelisAuthenticationRequired));
    } else {
      contentValues.putNull("isAuthenticationRequired");
    }
    if (((java.lang.String)model.getSubmitButtonTitle()) != null)  {
      contentValues.put("submitButtonTitle",((java.lang.String)model.getSubmitButtonTitle()));
    } else {
      contentValues.putNull("submitButtonTitle");
    }
    if (((java.lang.String)model.getPlaceholder()) != null)  {
      contentValues.put("placeholder",((java.lang.String)model.getPlaceholder()));
    } else {
      contentValues.putNull("placeholder");
    }
    Object modelshouldShowTextInput = FlowManager.getTypeConverterForClass(java.lang.Boolean.class).getDBValue(model.getShouldShowTextInput());
    if (modelshouldShowTextInput != null)  {
      contentValues.put("shouldShowTextInput",((java.lang.Integer)modelshouldShowTextInput));
    } else {
      contentValues.putNull("shouldShowTextInput");
    }
    contentValues.put("position",((int)model.getPosition()));

  }

  @Override
  public boolean exists(ActionObject model) {
    return new Select().from(ActionObject.class).where(getPrimaryModelWhere(model)).hasData();
  }

  @Override
  public void loadFromCursor(Cursor cursor, ActionObject model) {
    int indexcategoryId = cursor.getColumnIndex("categoryId");
    if (indexcategoryId != -1)  {
      if (cursor.isNull(indexcategoryId)) {
        model.setCategoryId(null);
      } else {
        model.setCategoryId(cursor.getString(indexcategoryId));
      }
    }
    int indexactionId = cursor.getColumnIndex("actionId");
    if (indexactionId != -1)  {
      if (cursor.isNull(indexactionId)) {
        model.setActionId(null);
      } else {
        model.setActionId(cursor.getString(indexactionId));
      }
    }
    int indexbuttonTitle = cursor.getColumnIndex("buttonTitle");
    if (indexbuttonTitle != -1)  {
      if (cursor.isNull(indexbuttonTitle)) {
        model.setButtonTitle(null);
      } else {
        model.setButtonTitle(cursor.getString(indexbuttonTitle));
      }
    }
    int indexisDestructive = cursor.getColumnIndex("isDestructive");
    if (indexisDestructive != -1)  {
      if (cursor.isNull(indexisDestructive)) {
        model.setIsDestructive(null);
      } else {
        model.setIsDestructive(((java.lang.Boolean)FlowManager.getTypeConverterForClass(java.lang.Boolean.class).getModelValue(cursor.getInt(indexisDestructive))));
      }
    }
    int indexisAuthenticationRequired = cursor.getColumnIndex("isAuthenticationRequired");
    if (indexisAuthenticationRequired != -1)  {
      if (cursor.isNull(indexisAuthenticationRequired)) {
        model.setIsAuthenticationRequired(null);
      } else {
        model.setIsAuthenticationRequired(((java.lang.Boolean)FlowManager.getTypeConverterForClass(java.lang.Boolean.class).getModelValue(cursor.getInt(indexisAuthenticationRequired))));
      }
    }
    int indexsubmitButtonTitle = cursor.getColumnIndex("submitButtonTitle");
    if (indexsubmitButtonTitle != -1)  {
      if (cursor.isNull(indexsubmitButtonTitle)) {
        model.setSubmitButtonTitle(null);
      } else {
        model.setSubmitButtonTitle(cursor.getString(indexsubmitButtonTitle));
      }
    }
    int indexplaceholder = cursor.getColumnIndex("placeholder");
    if (indexplaceholder != -1)  {
      if (cursor.isNull(indexplaceholder)) {
        model.setPlaceholder(null);
      } else {
        model.setPlaceholder(cursor.getString(indexplaceholder));
      }
    }
    int indexshouldShowTextInput = cursor.getColumnIndex("shouldShowTextInput");
    if (indexshouldShowTextInput != -1)  {
      if (cursor.isNull(indexshouldShowTextInput)) {
        model.setShouldShowTextInput(null);
      } else {
        model.setShouldShowTextInput(((java.lang.Boolean)FlowManager.getTypeConverterForClass(java.lang.Boolean.class).getModelValue(cursor.getInt(indexshouldShowTextInput))));
      }
    }
    int indexposition = cursor.getColumnIndex("position");
    if (indexposition != -1)  {
      model.setPosition(cursor.getInt(indexposition));
    }
  }

  @Override
  public ConditionQueryBuilder<ActionObject> getPrimaryModelWhere(ActionObject model) {
    return new ConditionQueryBuilder<ActionObject>(ActionObject.class, Condition.column(ActionObject$Table.ACTIONID).is((model.getActionId())));
  }

  @Override
  public ConditionQueryBuilder<ActionObject> createPrimaryModelWhere() {
    return new ConditionQueryBuilder<ActionObject>(ActionObject.class, Condition.column(ActionObject$Table.ACTIONID).is("?"));
  }

  @Override
  public String getCreationQuery() {
    return "CREATE TABLE IF NOT EXISTS `ActionObject`(`categoryId` TEXT, `actionId` TEXT, `buttonTitle` TEXT, `isDestructive` INTEGER, `isAuthenticationRequired` INTEGER, `submitButtonTitle` TEXT, `placeholder` TEXT, `shouldShowTextInput` INTEGER, `position` INTEGER, PRIMARY KEY(`actionId`));";
  }

  @Override
  public final ActionObject newInstance() {
    return new expo.modules.notifications.action.ActionObject();
  }
}
