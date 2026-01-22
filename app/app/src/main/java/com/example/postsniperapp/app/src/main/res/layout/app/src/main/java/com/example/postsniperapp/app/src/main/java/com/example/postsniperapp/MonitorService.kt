package com.example.postsniperapp

import android.app.*
import android.content.Intent
import android.os.*
import androidx.core.app.NotificationCompat

class MonitorService : Service() {

    private var username = ""
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var checker: InstaChecker

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        username = intent?.getStringExtra("username") ?: ""
        checker = InstaChecker(this, username)

        startForeground(1, createNotification())
        startLoop()

        return START_STICKY
    }

    private fun startLoop() {
        handler.post(object : Runnable {
            override fun run() {
                checker.check()
                handler.postDelayed(this, 10000) // 10 seconds
            }
        })
    }

    private fun createNotification(): Notification {
        val channelId = "insta_alert"
        val channel = NotificationChannel(
            channelId,
            "PostSniperApp",
            NotificationManager.IMPORTANCE_LOW
        )
        getSystemService(NotificationManager::class.java)
            .createNotificationChannel(channel)

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("PostSniperApp")
            .setContentText("Monitoring Instagram...")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .build()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
