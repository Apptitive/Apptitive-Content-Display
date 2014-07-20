package com.apptitive.content_display.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Vibrator;

import com.apptitive.content_display.R;
import com.apptitive.content_display.services.RingtoneService;
import com.apptitive.content_display.utilities.Constants;
import com.apptitive.content_display.utilities.PreferenceHelper;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        PreferenceHelper preferenceHelper = new PreferenceHelper(context);

       /* boolean isVibrate = preferenceHelper.getBoolean(context.getString(R.string.pref_key_sync_settings));
        if (isVibrate) {
            setAlarmVibration(context);
        }*/

    }


}
