package com.apptitive.content_display.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.apptitive.content_display.interfaces.LoaderListener;
import com.apptitive.content_display.utilities.LogUtil;

/**
 * Created by Sharif on 7/23/2014.
 */
public class SyncResponseReceiver extends BroadcastReceiver {
    private LoaderListener loaderListener;

    public SyncResponseReceiver(LoaderListener loaderListener) {
        this.loaderListener = loaderListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getStringExtra("status").equals("started")) {
            LogUtil.LOGE("loading started");
            loaderListener.onLoadStarted();
        } else if (intent.getStringExtra("status").equals("finished")) {
            LogUtil.LOGE("loading finished");
            loaderListener.onLoadFinished();
        } else if (intent.getStringExtra("status").equals("failed")) {
            loaderListener.onLoadFailed();
        }
    }
}
