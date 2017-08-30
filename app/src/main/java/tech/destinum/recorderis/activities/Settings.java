package tech.destinum.recorderis.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import tech.destinum.recorderis.R;

public class Settings extends BaseActivity {

    public Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        super.onCreateDrawer();

        mButton = (Button) findViewById(R.id.button_try);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, Home.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(Settings.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                int mNotificationId = 001;
                Notification notification = new Notification.Builder(v.getContext())
                        .setContentTitle("Recorderis")
                        .setContentText("Esta por vencerse un Documento, PILAS!")
                        .setSmallIcon(R.mipmap.ic_notification)
                        .setContentIntent(pendingIntent).build();

                NotificationManager notificationManager = (NotificationManager) v.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                notification.flags |= Notification.FLAG_AUTO_CANCEL;
                notificationManager.notify(mNotificationId, notification);
            }
        });

    }
}
