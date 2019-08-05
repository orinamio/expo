package expo.modules.notifications.schedulers;

import android.content.Context;
import expo.modules.notifications.exceptions.UnableToScheduleException;

public interface Scheduler {

  void schedule(String action) throws UnableToScheduleException;

  String getIdAsString();

  String getOwnerExperienceId();

  void cancel();

  boolean canBeRescheduled();

  String saveAndGetId();

  void setApplicationContext(Context context);

  void remove();

}
