package tech.destinum.recorderis.utils;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

import tech.destinum.recorderis.R;
import tech.destinum.recorderis.activities.Home;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent notificationIntent = new Intent(context, Home.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(Home.class);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Notification notification = builder
                .setContentTitle("Recorderis")
                .setContentText("Esta por vencerse un Documento, PILAS!")
                .setSmallIcon(R.mipmap.ic_notification)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
//    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//
//    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//    Notification notification = new Notification.Builder(context)
//            .setContentTitle("Recorderis")
//            .setContentText("Esta por vencerse un Documento, PILAS!")
//            .setSmallIcon(R.mipmap.ic_notification)
//            .setContentIntent(pendingIntent).build();
//
//    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notification.flags |= Notification.FLAG_AUTO_CANCEL;
//                notificationManager.notify(1, notification);
//
//
//                Calendar cal = Calendar.getInstance();
//                long time = cal.getTimeInMillis() + 5000;
//                Log.d("onreceive", String.valueOf(time));
//                alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent);