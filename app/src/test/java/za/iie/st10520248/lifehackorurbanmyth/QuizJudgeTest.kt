package za.iie.st10520248.lifehackorurbanmyth

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class QuizJudgeTest {

    @Test
    fun correctAnswerReturnsTrueWhenSelectionMatchesQuestionType() {
        val question = HackQuestion(
            statement = "Using a tray to organise keys reduces clutter.",
            isRealHack = true,
            explanation = "Hack"
        )

        assertTrue(QuizJudge.isCorrectAnswer(question, userSelectedHack = true))
    }

    @Test
    fun scoreFeedbackRewardsHighScores() {
        val feedback = QuizJudge.scoreFeedback(score = 5, total = 6)

        assertEquals(
            "Master Hacker! You can separate useful tips from risky internet myths.",
            feedback
        )
    }

    @Test
    fun reviewTextIncludesKnownQuestionAndAnswerLabel() {
        val reviewText = QuizJudge.buildReviewText()

        assertTrue(reviewText.contains("1. Switching your phone to airplane mode while charging can help it charge faster."))
        assertTrue(reviewText.contains("Answer: Hack"))
    }
}
