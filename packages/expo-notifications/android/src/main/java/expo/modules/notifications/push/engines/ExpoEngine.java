package expo.modules.notifications.push.engines;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.Semaphore;

import expo.modules.notifications.push.CompletionHandler;
import expo.modules.notifications.push.engines.configuration.Configuration;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ExpoEngine implements expo.modules.notifications.push.Engine {

    private static String PREF_NAME = "expo.push.notification.engine";
    public static String DEVICE_ID = "deviceId";

    @Override
    public void getToken(String appId, Context context, final CompletionHandler completionHandler) {
        new Thread(
            () -> {
                String expoPushToken = getExpoPushToken(appId, context.getApplicationContext());
                completionHandler.handle(expoPushToken);
            }
        ).start();
    }

    @Override
    public void onFirebaseToken(String token, Context context) {
        Intent intent = new Intent(context, ExpoEngineIntentService.class);
        intent.putExtra("token", token);
        context.startService(intent);
    }

    static synchronized String getExpoPushToken(String appId, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(appId)) {
            return sharedPreferences.getString(appId, "");
        } else {
            String expoToken = getFromExpoServer(appId, context);
            if (expoToken == null) {
                return null;
            }

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(appId, expoToken);
            editor.commit();
            return expoToken;
        }
    }

    static String getFromExpoServer(String appId, Context context) {
        Semaphore semaphore = new Semaphore(1, true);
        String token = null;
        final ArrayList<String> stringWrapper = new ArrayList<>();

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()) {
                    semaphore.release();
                    return;
                }
                String token = task.getResult().getToken();
                stringWrapper.add(token);
                semaphore.release();
            }
        });

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (stringWrapper.size() > 0) {
            token = stringWrapper.get(0);
        } else {
            return null;
        }

        JSONObject params = new JSONObject();
        try {
            params.put("deviceId", getOrCreateDeviceId(context));
            params.put("experienceId", appId);
            params.put("appId", context.getApplicationContext().getPackageName());
            params.put("deviceToken", token);
            params.put("type", "fcm");
            params.put("development", false);
        } catch (JSONException e) {
            return null;
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), params.toString());
        Request request = createGetTokenRequest(body);

        OkHttpClient client = new OkHttpClient();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                return null;
            }
            JSONObject result = null;
            result = new JSONObject(response.body().string());
            JSONObject data = result.getJSONObject("data");
            return data.getString("expoPushToken");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    static private Request createGetTokenRequest(RequestBody body) {
        Request.Builder builder = new Request.Builder();
        builder.url(Configuration.SERVER_BASE_ADDRESS + "getExpoPushToken");
        builder.header("Exponent-Platform", "android");
        builder.post(body);
        builder.header("Content-Type", "application/json");
        return builder.build();
    }

    static synchronized String getOrCreateDeviceId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(DEVICE_ID)) {
            return sharedPreferences.getString(DEVICE_ID, "");
        } else {
            UUID deviceId = UUID.randomUUID();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(DEVICE_ID, deviceId.toString());
            editor.commit();
            return deviceId.toString();
        }
    }

}
