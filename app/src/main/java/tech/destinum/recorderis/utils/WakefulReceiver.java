package tech.destinum.recorderis.utils;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

import tech.destinum.recorderis.R;
import tech.destinum.recorderis.activities.Home;

public class WakefulReceiver extends WakefulBroadcastReceiver {
    // provides access to the system alarm services.
    private AlarmManager mAlarmManager;

    public void onReceive(Context context, Intent intent) {
        //// TODO: post notification
        WakefulReceiver.completeWakefulIntent(intent);
    }

    /**
     * Sets the next alarm to run. When the alarm fires,
     * the app broadcasts an Intent to this WakefulBroadcastReceiver.
     *
     * @param context the context of the app's Activity.
     */
    public void setAlarm(Context context, long _id) {
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, WakefulReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        //// TODO: use calendar.add(Calendar.SECOND,MINUTE,HOUR, int);
//        calendar.add(Calendar.HOUR, 10);

        //ALWAYS recompute the calendar after using add, set, roll
        Date date = calendar.getTime();
        Log.d("date", date.toString());

        mAlarmManager.setExact(AlarmManager.RTC_WAKEUP, date.getTime(), alarmIntent);
        Intent intent2 = new Intent(context, Home.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new Notification.Builder(context)
                .setContentTitle("Recorderis")
                .setContentText("Esta por vencerse un Documento, PILAS!")
                .setSmallIcon(R.mipmap.ic_notification)
                .setContentIntent(pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify((int)_id, notification);
        // Enable {@code BootReceiver} to automatically restart when the
        // device is rebooted.
        //// TODO: you may need to reference the context by ApplicationActivity.class
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    /**
     * Cancels the next alarm from running. Removes any intents set by this
     * WakefulBroadcastReceiver.
     *
     * @param context the context of the app's Activity
     */
    public void cancelAlarm(Context context, long _id) {
        Log.d("WakefulAlarmReceiver", "{cancelAlarm}");

        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, WakefulReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        mAlarmManager.cancel(alarmIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel((int) _id);

        // Disable {@code BootReceiver} so that it doesn't automatically restart when the device is rebooted.
        //// TODO: you may need to reference the context by ApplicationActivity.class
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}