import android.content.*
import android.os.*
import android.widget.Toast
import org.jsoup.Jsoup
import java.lang.Exception

class InstaChecker(
    private val context: Context,
    private val username: String
) {
    private val prefs = context.getSharedPreferences("insta", Context.MODE_PRIVATE)

    fun check() {
        try {
            val url = "https://www.instagram.com/$username/"
            val doc = Jsoup.connect(url).get()
            val html = doc.html()

            val regex = """"shortcode":"(.*?)"""".toRegex()
            val newId = regex.find(html)?.groupValues?.get(1)

            val oldId = prefs.getString("last_post", "")

            if (newId != null && newId != oldId) {
                prefs.edit().putString("last_post", newId).apply()
                val link = "https://www.instagram.com/p/$newId/"
                triggerAlert(link)
            }

        } catch (e: Exception) {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(context, "Error checking Instagram", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun triggerAlert(link: String) {
        // Copy link to clipboard
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Insta Link", link)
        clipboard.setPrimaryClip(clip)

        // Start AlertActivity
        val intent = Intent(context, AlertActivity::class.java)
        intent.putExtra("link", link)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}
