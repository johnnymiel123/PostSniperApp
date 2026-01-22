import android.media.RingtoneManager
import android.os.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import android.os.Vibrator

class AlertActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Make full-screen alert and turn on screen
        window.addFlags(
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_alert)

        val link = intent.getStringExtra("link")
        findViewById<TextView>(R.id.linkText).text = link

        // Vibrate phone
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(2000)

        // Play alarm sound (system default, can be customized in phone settings)
        val alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val ringtone = RingtoneManager.getRingtone(this, alarmUri)
        ringtone.play()
    }
}
