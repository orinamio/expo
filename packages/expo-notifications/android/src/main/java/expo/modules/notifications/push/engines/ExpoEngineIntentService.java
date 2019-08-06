package expo.modules.notifications.push.engines;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import expo.modules.notifications.push.engines.configuration.Configuration;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ExpoEngineIntentService extends IntentService {

    private ExpoEngineIntentService() {
        super("ExpoEngineIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String deviceId = ExpoEngine.getOrCreateDeviceId(getApplicationContext());
        String token = intent.getStringExtra("token");
        try {
            JSONObject params = new JSONObject();
            params.put("deviceToken", token);
            params.put("deviceId", deviceId);
            params.put("appId", getApplicationContext().getPackageName());
            params.put("type", "fcm");

            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), params.toString());
            Request request =createRequest(body);

            OkHttpClient client = new OkHttpClient();
            client.newCall(request).execute();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private Request createRequest(RequestBody body) {
        Request.Builder builder = new Request.Builder();
        builder.url(Configuration.SERVER_BASE_ADDRESS + "updateDeviceToken");
        builder.header("Exponent-Platform", "android");
        builder.post(body);
        builder.header("Content-Type", "application/json");
        return builder.build();
    }
}
