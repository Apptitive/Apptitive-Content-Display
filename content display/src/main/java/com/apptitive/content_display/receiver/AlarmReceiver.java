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

/*
        boolean isAlarmSelected = preferenceHelper.getBoolean(context.getString(R.string.alarm_switch));
        if (!isAlarmSelected) {
            return;
        }*/


        boolean isVibrate = preferenceHelper.getBoolean(context.getString(R.string.pref_key_alarm_vibrate));
        if (isVibrate) {
            setAlarmVibration(context);
        }
        String ringTonName = preferenceHelper.getString(context.getString(R.string.pref_key_alarm_ringtone), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM).toString());
        if (ringTonName != null) {
            Intent ringTonIntent = new Intent(context, RingtoneService.class);
            ringTonIntent.putExtra(Constants.KEY_RINGTONE_NAME, ringTonName);
            context.startService(ringTonIntent);
        }

    }

    private void setAlarmVibration(Context context) {
        Vibrator v = (Vibrator) context
                .getSystemService(Context.VIBRATOR_SERVICE);
        // for 3 seconds
        long milliseconds = 3000;
        long pattern[] = {0, milliseconds, 200, 300, 500};
        v.vibrate(pattern, -1);
    }


}