package tech.destinum.recorderis.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
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

                Notification notification = new Notification.Builder(Settings.this)
                        .setContentTitle("Recorderis")
                        .setContentText("Esta por vencerse un Documento, PILAS!")
                        .setSmallIcon(R.mipmap.ic_notification)
                        .setContentIntent(pendingIntent).build();

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notification.flags |= Notification.FLAG_AUTO_CANCEL;

                Notification.InboxStyle big = new Notification.InboxStyle();

                big.setBigContentTitle("Recorderis");
                big.setSummaryText("PILAS!! Su Revision Tecnico Mecanica esta próxima a vencerse!! El 25/Noviembre/2018");



                notificationManager.notify(0, notification);
            }
        });

    }
}
