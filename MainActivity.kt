import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startBtn = findViewById<Button>(R.id.startBtn)
        val stopBtn = findViewById<Button>(R.id.stopBtn)
        val usernameInput = findViewById<EditText>(R.id.usernameInput)

        startBtn.setOnClickListener {
            val username = usernameInput.text.toString()
            val intent = Intent(this, MonitorService::class.java)
            intent.putExtra("username", username)
            startService(intent)
        }

        stopBtn.setOnClickListener {
            stopService(Intent(this, MonitorService::class.java))
        }
    }
}
