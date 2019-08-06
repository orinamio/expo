package expo.modules.notifications.push;

import android.content.Context;

public interface Engine {

    void getToken(String appId, Context context, CompletionHandler completionHandler);

    void onFirebaseToken(String token, Context context);

}
