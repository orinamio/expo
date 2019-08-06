package expo.modules.notifications.push.engines;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import expo.modules.notifications.push.CompletionHandler;

public class BareEngine implements expo.modules.notifications.push.Engine {

    @Override
    public void getToken(String appId, Context context, CompletionHandler completionHandler) {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            completionHandler.handle(null);
                        }
                        String token = task.getResult().getToken();
                        completionHandler.handle(token);
                    }
                });
    }

    @Override
    public void onFirebaseToken(String token, Context context) {
        // no-op
    }

}
