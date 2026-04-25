package za.iie.st10520248.lifehackorurbanmyth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        val reviewTextView: TextView = findViewById(R.id.tvReviewContent)
        val backButton: Button = findViewById(R.id.btnBackToHome)

        // The full review list helps the user learn from every card after the quiz ends.
        reviewTextView.text = QuizJudge.buildReviewText()

        backButton.setOnClickListener {
            Log.d(TAG, "Back to home clicked from review screen")
            val homeIntent = Intent(this, MainActivity::class.java)
            homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(homeIntent)
            finish()
        }

        Log.d(TAG, "Review screen opened")
    }

    companion object {
        private const val TAG = "ReviewActivity"
    }
}
