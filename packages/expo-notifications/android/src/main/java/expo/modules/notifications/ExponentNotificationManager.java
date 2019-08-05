package expo.modules.notifications;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;

import host.exp.exponent.Constants;
import host.exp.exponent.ExponentManifest;
import host.exp.exponent.analytics.EXL;
import host.exp.exponent.di.NativeModuleDepsProvider;
import host.exp.exponent.kernel.KernelConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import expo.modules.notifications.insecurescheduler.ScheduledNotificationReceiver;
import host.exp.exponent.storage.ExponentSharedPreferences;
import host.exp.expoview.R;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ExponentNotificationManager {

  @Inject
  ExponentSharedPreferences mExponentSharedPreferences;

  private static String TAG = ExponentNotificationManager.class.getSimpleName();

  private Context mContext;

  private static Set<String> mNotificationChannelGroupIds = new HashSet<>();
  private static boolean mIsExpoPersistentNotificationCreated = false;

  public ExponentNotificationManager(Context context) {
    mContext = context;
    NativeModuleDepsProvider.getInstance().inject(ExponentNotificationManager.class, this);
  }

  public static String getScopedChannelId(String appId, String channelId) {
    if (Constants.isStandaloneApp()) {
      return channelId;
    } else {
      return appId + "/" + channelId;
    }
  }

  public void maybeCreateNotificationChannelGroup(JSONObject manifest) {
    if (Constants.isStandaloneApp()) {
      // currently we only support groups in the client, with one group per experience
      return;
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      try {
        String appId = manifest.getString(ExponentManifest.MANIFEST_ID_KEY);
        if (!mNotificationChannelGroupIds.contains(appId)) {
          String channelName = manifest.optString(ExponentManifest.MANIFEST_NAME_KEY, appId);
          NotificationChannelGroup group = new NotificationChannelGroup(appId, channelName);
          mContext.getSystemService(NotificationManager.class).createNotificationChannelGroup(group);

          mNotificationChannelGroupIds.add(appId);
        }
      } catch (Exception e) {
        EXL.e(TAG, "Could not create notification channel: " + e.getMessage());
      }
    }
  }

  public void maybeCreateExpoPersistentNotificationChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      if (mIsExpoPersistentNotificationCreated) {
        return;
      }

      NotificationManager manager = mContext.getSystemService(NotificationManager.class);

      NotificationChannel channel = new NotificationChannel(
          NotificationConstants.NOTIFICATION_EXPERIENCE_CHANNEL_ID,
          mContext.getString(R.string.persistent_notification_channel_name),
          NotificationManager.IMPORTANCE_DEFAULT);
      channel.setSound(null, null);
      channel.setDescription(mContext.getString(R.string.persistent_notification_channel_desc));

      if (!Constants.isStandaloneApp()) {
        NotificationChannelGroup group = new NotificationChannelGroup(
            NotificationConstants.NOTIFICATION_EXPERIENCE_CHANNEL_GROUP_ID,
            mContext.getString(R.string.persistent_notification_channel_group));
        manager.createNotificationChannelGroup(group);
        channel.setGroup(NotificationConstants.NOTIFICATION_EXPERIENCE_CHANNEL_GROUP_ID);
      }
      manager.createNotificationChannel(channel);

      mIsExpoPersistentNotificationCreated = true;
    }
  }

  public void createNotificationChannel(String appId, NotificationChannel channel) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      if (!Constants.isStandaloneApp()) {
        channel.setGroup(appId);
      }
      mContext.getSystemService(NotificationManager.class).createNotificationChannel(channel);
    }
  }

  public void saveChannelSettings(String appId, String channelId, HashMap details) {
    try {
      JSONObject metadata = mExponentSharedPreferences.getExperienceMetadata(appId);
      if (metadata == null) {
        metadata = new JSONObject();
      }

      JSONObject allChannels;
      if (metadata.has(ExponentSharedPreferences.EXPERIENCE_METADATA_NOTIFICATION_CHANNELS)) {
        allChannels = metadata.getJSONObject(ExponentSharedPreferences.EXPERIENCE_METADATA_NOTIFICATION_CHANNELS);
      } else {
        allChannels = new JSONObject();
      }

      JSONObject channel = new JSONObject(details);

      allChannels.put(channelId, channel);
      metadata.put(ExponentSharedPreferences.EXPERIENCE_METADATA_NOTIFICATION_CHANNELS, allChannels);

      mExponentSharedPreferences.updateExperienceMetadata(appId, metadata);
    } catch (JSONException e) {
      EXL.e(TAG, "Could not store channel in shared preferences: " + e.getMessage());
    }
  }

  public JSONObject readChannelSettings(String appId, String channelId) {
    try {
      JSONObject metadata = mExponentSharedPreferences.getExperienceMetadata(appId);
      if (metadata == null) {
        metadata = new JSONObject();
      }

      JSONObject allChannels;
      if (metadata.has(ExponentSharedPreferences.EXPERIENCE_METADATA_NOTIFICATION_CHANNELS)) {
        allChannels = metadata.getJSONObject(ExponentSharedPreferences.EXPERIENCE_METADATA_NOTIFICATION_CHANNELS);
      } else {
        allChannels = new JSONObject();
      }

      return allChannels.optJSONObject(channelId);
    } catch (JSONException e) {
      EXL.e(TAG, "Could not read channel from shared preferences: " + e.getMessage());
    }
    return null;
  }

  public NotificationChannel getNotificationChannel(String appId, String channelId) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      return mContext.getSystemService(NotificationManager.class).getNotificationChannel(getScopedChannelId(appId, channelId));
    } else {
      return null;
    }
  }

  public void deleteNotificationChannel(String appId, String channelId) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      mContext.getSystemService(NotificationManager.class).deleteNotificationChannel(getScopedChannelId(appId, channelId));
    }
  }

  public void notify(String appId, int id, Notification notification) {
    NotificationManagerCompat.from(mContext).notify(appId, id, notification);

    try {
      JSONObject metadata = mExponentSharedPreferences.getExperienceMetadata(appId);
      if (metadata == null) {
        metadata = new JSONObject();
      }

      JSONArray notifications = metadata.optJSONArray(ExponentSharedPreferences.EXPERIENCE_METADATA_ALL_NOTIFICATION_IDS);
      if (notifications == null) {
        notifications = new JSONArray();
      }
      notifications.put(id);
      metadata.put(ExponentSharedPreferences.EXPERIENCE_METADATA_ALL_NOTIFICATION_IDS, notifications);
      mExponentSharedPreferences.updateExperienceMetadata(appId, metadata);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  public void cancel(String appId, int id) {
    NotificationManagerCompat.from(mContext).cancel(id);
    try {
      JSONObject metadata = mExponentSharedPreferences.getExperienceMetadata(appId);
      if (metadata == null) {
        return;
      }
      JSONArray oldNotifications = metadata.optJSONArray(ExponentSharedPreferences.EXPERIENCE_METADATA_ALL_NOTIFICATION_IDS);
      if (oldNotifications == null) {
        return;
      }
      JSONArray newNotifications = new JSONArray();
      for (int i = 0; i < oldNotifications.length(); i++) {
        if (oldNotifications.getInt(i) != id) {
          newNotifications.put(oldNotifications.getInt(i));
        }
      }
      metadata.put(ExponentSharedPreferences.EXPERIENCE_METADATA_ALL_NOTIFICATION_IDS, newNotifications);
      mExponentSharedPreferences.updateExperienceMetadata(appId, metadata);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  public void cancelAll(String appId) {
    try {
      JSONObject metadata = mExponentSharedPreferences.getExperienceMetadata(appId);
      if (metadata == null) {
        return;
      }
      JSONArray notifications = metadata.optJSONArray(ExponentSharedPreferences.EXPERIENCE_METADATA_ALL_NOTIFICATION_IDS);
      if (notifications == null) {
        return;
      }
      NotificationManagerCompat manager = NotificationManagerCompat.from(mContext);
      for (int i = 0; i < notifications.length(); i++) {
        manager.cancel(appId, notifications.getInt(i));
      }
      metadata.put(ExponentSharedPreferences.EXPERIENCE_METADATA_ALL_NOTIFICATION_IDS, null);
      metadata.put(ExponentSharedPreferences.EXPERIENCE_METADATA_UNREAD_REMOTE_NOTIFICATIONS, null);

      mExponentSharedPreferences.updateExperienceMetadata(appId, metadata);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  public void schedule(String appId, int id, HashMap notification, long time, Long interval) throws ClassNotFoundException {
    Intent notificationIntent = new Intent(mContext, ScheduledNotificationReceiver.class);

    notificationIntent.setType(appId);
    notificationIntent.setAction(String.valueOf(id));

    notificationIntent.putExtra(KernelConstants.NOTIFICATION_ID_KEY, id);
    notificationIntent.putExtra(KernelConstants.NOTIFICATION_OBJECT_KEY, notification);

    PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

    AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);

    if (interval != null) {
      alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, time, interval, pendingIntent);
    } else {
      alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, time, pendingIntent);
    }

    try {
      JSONObject metadata = mExponentSharedPreferences.getExperienceMetadata(appId);
      if (metadata == null) {
        metadata = new JSONObject();
      }

      JSONArray notifications = metadata.optJSONArray(ExponentSharedPreferences.EXPERIENCE_METADATA_ALL_SCHEDULED_NOTIFICATION_IDS);
      if (notifications == null) {
        notifications = new JSONArray();
      }
      notifications.put(id);
      metadata.put(ExponentSharedPreferences.EXPERIENCE_METADATA_ALL_SCHEDULED_NOTIFICATION_IDS, notifications);
      mExponentSharedPreferences.updateExperienceMetadata(appId, metadata);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  public void cancelScheduled(String appId, int id) throws ClassNotFoundException {
    Intent notificationIntent = new Intent(mContext, ScheduledNotificationReceiver.class);

    notificationIntent.setType(appId);
    notificationIntent.setAction(String.valueOf(id));

    PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);

    alarmManager.cancel(pendingIntent);
    cancel(appId, id);
  }

  public void cancelAllScheduled(String appId) throws ClassNotFoundException {
    try {
      JSONObject metadata = mExponentSharedPreferences.getExperienceMetadata(appId);
      if (metadata == null) {
        return;
      }
      JSONArray notifications = metadata.optJSONArray(ExponentSharedPreferences.EXPERIENCE_METADATA_ALL_SCHEDULED_NOTIFICATION_IDS);
      if (notifications == null) {
        return;
      }
      for (int i = 0; i < notifications.length(); i++) {
        cancelScheduled(appId, notifications.getInt(i));
      }
      metadata.put(ExponentSharedPreferences.EXPERIENCE_METADATA_ALL_SCHEDULED_NOTIFICATION_IDS, null);
      mExponentSharedPreferences.updateExperienceMetadata(appId, metadata);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
}
