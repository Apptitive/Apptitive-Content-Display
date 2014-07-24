package com.apptitive.content_display;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.apptitive.content_display.sync.SyncUtils;

import java.util.Timer;
import java.util.TimerTask;


public class SplashScreen extends Activity {

    @Override
    public void onStart() {
        super.onStart();
       // SyncUtils.triggerManualSync();
        // EasyTracker.getInstance(this).activityStart(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        // EasyTracker.getInstance(this).activityStop(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_splash_screen);
        SyncUtils.createSyncAccount(this);
        SyncUtils.triggerManualSync();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                startActivity(new Intent(SplashScreen.this, StartActivity.class));
                finish();
            }
        }, 2000);
    }
}
