package za.iie.st10520248.lifehackorurbanmyth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "Welcome screen opened")

        val startButton: Button = findViewById(R.id.btnStartQuiz)
        startButton.setOnClickListener {
            // The welcome screen only has one responsibility: send the user into the quiz flow.
            Log.d(TAG, "Start button clicked")
            startActivity(Intent(this, QuizActivity::class.java))
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
