package com.apptitive.content_display.utilities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.apptitive.content_display.receiver.AlarmReceiver;

import java.util.Calendar;

/**
 * Created by Sharif on 7/20/2014.
 */
public class AlarmUtil {

    public static void setUpAlarm(Context context, long repeatingDay) {
        if (repeatingDay == -1) {
            return;
        }
        LogUtil.LOGE("inside alarm class" + repeatingDay);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
              AlarmManager.INTERVAL_DAY* repeatingDay, alarmIntent);
    }
}
