package com.example.services.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.services.*
import com.example.services.services.MyService

class ServiceActivity : AppCompatActivity() {

    companion object {
        const val CHANNEL1 = "channel1"
        const val NOTIFICATION_ID = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        startNormalServices()
    }




    private fun startNormalServices() {
        var intent = Intent(this, MyService::class.java)
        intent.putExtra("KEY", "Hello")
        //Use same instance
//        startService(intent)
        startService(Intent(this, MyService::class.java))
        startService(Intent(this, MyService::class.java))
        startService(Intent(this, MyService::class.java))
        startService(Intent(this, MyService::class.java))

        //The instance tie to same main thread
        Handler()
        Handler()
        Handler()

        createNotification()
    }

    override fun onStop() {
        super.onStop()

        //Not mandatory to stop service
        stopService(Intent(this, MyService::class.java))

    }


//****************Notification*********************************************************************************************

    private fun createNotification() {
        createNotificationChannel()
        var notification = NotificationCompat.Builder(this,
            CHANNEL1
        )
        notification.setSmallIcon(R.drawable.ic_baseline_notifications_none_24)
        notification.priority = NotificationCompat.PRIORITY_DEFAULT
        notification.setContentTitle("Service started")
        notification.setContentText("Service is running")
        var notificationManagerCompat = NotificationManagerCompat.from(this)
        notificationManagerCompat.notify(NOTIFICATION_ID,notification.build())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    CHANNEL1, "Notification Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
                )
            )

        }
    }
}