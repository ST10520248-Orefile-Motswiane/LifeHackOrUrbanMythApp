package za.iie.st10520248.lifehackorurbanmyth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private lateinit var progressTextView: TextView
    private lateinit var questionTextView: TextView
    private lateinit var feedbackTextView: TextView
    private lateinit var scoreTextView: TextView
    private lateinit var hackButton: Button
    private lateinit var mythButton: Button
    private lateinit var nextButton: Button

    private var currentIndex = 0
    private var score = 0
    private var questionAnswered = false
    private var feedbackMessage = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        progressTextView = findViewById(R.id.tvQuestionProgress)
        questionTextView = findViewById(R.id.tvQuestionStatement)
        feedbackTextView = findViewById(R.id.tvAnswerFeedback)
        scoreTextView = findViewById(R.id.tvRunningScore)
        hackButton = findViewById(R.id.btnHack)
        mythButton = findViewById(R.id.btnMyth)
        nextButton = findViewById(R.id.btnNextQuestion)

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0)
            score = savedInstanceState.getInt(KEY_SCORE, 0)
            questionAnswered = savedInstanceState.getBoolean(KEY_ANSWERED, false)
            feedbackMessage = savedInstanceState.getString(KEY_FEEDBACK, "")
        }

        hackButton.setOnClickListener { submitAnswer(userSelectedHack = true) }
        mythButton.setOnClickListener { submitAnswer(userSelectedHack = false) }
        nextButton.setOnClickListener { goToNextQuestion() }

        loadQuestion()
        Log.d(TAG, "Quiz screen opened at question index $currentIndex")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX, currentIndex)
        outState.putInt(KEY_SCORE, score)
        outState.putBoolean(KEY_ANSWERED, questionAnswered)
        outState.putString(KEY_FEEDBACK, feedbackMessage)
    }

    private fun loadQuestion() {
        val question = HackQuestionRepository.questions[currentIndex]
        val totalQuestions = HackQuestionRepository.questions.size

        progressTextView.text = getString(
            R.string.question_progress_format,
            currentIndex + 1,
            totalQuestions
        )
        questionTextView.text = question.statement
        scoreTextView.text = getString(R.string.running_score_format, score)

        feedbackTextView.text = feedbackMessage
        feedbackTextView.visibility = if (feedbackMessage.isBlank()) View.GONE else View.VISIBLE

        hackButton.isEnabled = !questionAnswered
        mythButton.isEnabled = !questionAnswered
        nextButton.isEnabled = questionAnswered

        Log.d(TAG, "Loaded question ${currentIndex + 1} of $totalQuestions")
    }

    private fun submitAnswer(userSelectedHack: Boolean) {
        if (questionAnswered) {
            Log.d(TAG, "Answer ignored because the current question is already marked")
            return
        }

        val question = HackQuestionRepository.questions[currentIndex]
        val wasCorrect = QuizJudge.isCorrectAnswer(question, userSelectedHack)

        if (wasCorrect) {
            // The score only changes once per question after a correct match.
            score++
        }

        questionAnswered = true
        feedbackMessage = QuizJudge.answerFeedback(question, wasCorrect)
        feedbackTextView.text = feedbackMessage
        feedbackTextView.visibility = View.VISIBLE
        scoreTextView.text = getString(R.string.running_score_format, score)

        hackButton.isEnabled = false
        mythButton.isEnabled = false
        nextButton.isEnabled = true

        Log.d(
            TAG,
            "Answered question ${currentIndex + 1}; selectedHack=$userSelectedHack; correct=$wasCorrect; score=$score"
        )
    }

    private fun goToNextQuestion() {
        if (!questionAnswered) {
            Snackbar.make(nextButton, R.string.answer_before_next, Snackbar.LENGTH_SHORT).show()
            Log.d(TAG, "Next button blocked because no answer was chosen")
            return
        }

        // Moving the index is the loop mechanism that walks through the flashcards.
        currentIndex++
        feedbackMessage = ""
        questionAnswered = false

        if (currentIndex < HackQuestionRepository.questions.size) {
            loadQuestion()
        } else {
            Log.d(TAG, "Quiz finished with score $score")
            val scoreIntent = Intent(this, ScoreActivity::class.java).apply {
                putExtra(EXTRA_SCORE, score)
                putExtra(EXTRA_TOTAL, HackQuestionRepository.questions.size)
            }
            startActivity(scoreIntent)
            finish()
        }
    }

    companion object {
        private const val TAG = "QuizActivity"
        private const val KEY_INDEX = "key_index"
        private const val KEY_SCORE = "key_score"
        private const val KEY_ANSWERED = "key_answered"
        private const val KEY_FEEDBACK = "key_feedback"

        const val EXTRA_SCORE = "extra_score"
        const val EXTRA_TOTAL = "extra_total"
    }
}
