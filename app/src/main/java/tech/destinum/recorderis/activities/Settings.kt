package tech.destinum.recorderis.activities

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button

import tech.destinum.recorderis.R

class Settings : BaseActivity() {

    private val mButton by lazy { findViewById(R.id.button_try) as Button}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        super.onCreateDrawer()

        mButton.setOnClickListener { v ->
            val intent = Intent(this@Settings, Home::class.java)
            val pendingIntent = PendingIntent.getActivity(this@Settings, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            val mNotificationId = 1
            val notification = Notification.Builder(v.context)
                    .setContentTitle("Recorderis")
                    .setContentText("Esta por vencerse un Documento, PILAS!")
                    .setSmallIcon(R.mipmap.ic_notification)
                    .setContentIntent(pendingIntent).build()

            val notificationManager = v.context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL
            notificationManager.notify(mNotificationId, notification)
        }

    }
}
