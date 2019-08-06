package expo.modules.notifications.push.engines;

import android.content.Context;

import expo.modules.notifications.push.CompletionHandler;
import expo.modules.notifications.push.Engine;

public class StabEngine implements Engine {

    @Override
    public void getToken(String appId, Context context, CompletionHandler completionHandler) {
        //no-op
    }

    @Override
    public void onFirebaseToken(String token, Context context) {
        //no-op

    }
}
