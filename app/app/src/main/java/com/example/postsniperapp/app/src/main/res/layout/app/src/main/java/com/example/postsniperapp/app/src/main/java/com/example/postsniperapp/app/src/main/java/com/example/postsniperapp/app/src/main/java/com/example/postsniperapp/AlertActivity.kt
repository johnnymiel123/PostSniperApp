package com.example.postsniperapp

import android.media.RingtoneManager
import android.os.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import android.os.Vibrator

class AlertActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_alert)

        val link = intent.getStringExtra("link")
        findViewById<TextView>(R.id.linkText).text = link

        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(2000)

        val alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val ringtone = RingtoneManager.getRingtone(this, alarmUri)
        ringtone.play()
    }
}
