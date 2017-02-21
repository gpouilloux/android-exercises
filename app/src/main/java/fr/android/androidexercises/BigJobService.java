package fr.android.androidexercises;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Messenger;
import android.widget.Toast;

public class BigJobService extends IntentService {

    public BigJobService() {
        super(BigJobService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            wait(5 * 1000);
        } catch (Exception e) {

        }
        sendBroadcast(intent);
    }
}
