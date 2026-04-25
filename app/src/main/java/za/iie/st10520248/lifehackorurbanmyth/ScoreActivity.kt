package za.iie.st10520248.lifehackorurbanmyth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val score = intent.getIntExtra(QuizActivity.EXTRA_SCORE, 0)
        val total = intent.getIntExtra(QuizActivity.EXTRA_TOTAL, HackQuestionRepository.questions.size)

        val scoreTitle: TextView = findViewById(R.id.tvFinalScore)
        val scoreFeedback: TextView = findViewById(R.id.tvFinalFeedback)
        val reviewButton: Button = findViewById(R.id.btnReviewAnswers)
        val restartButton: Button = findViewById(R.id.btnRestartQuiz)

        // The score screen converts the raw mark into readable end-user feedback.
        scoreTitle.text = getString(R.string.final_score_format, score, total)
        scoreFeedback.text = QuizJudge.scoreFeedback(score, total)

        reviewButton.setOnClickListener {
            Log.d(TAG, "Review button clicked")
            startActivity(Intent(this, ReviewActivity::class.java))
        }

        restartButton.setOnClickListener {
            Log.d(TAG, "Restart button clicked")
            val restartIntent = Intent(this, MainActivity::class.java)
            restartIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(restartIntent)
            finish()
        }

        Log.d(TAG, "Score screen opened with score $score/$total")
    }

    companion object {
        private const val TAG = "ScoreActivity"
    }
}
