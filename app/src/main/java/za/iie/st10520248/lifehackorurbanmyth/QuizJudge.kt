package za.iie.st10520248.lifehackorurbanmyth

object QuizJudge {

    fun isCorrectAnswer(question: HackQuestion, userSelectedHack: Boolean): Boolean {
        return question.isRealHack == userSelectedHack
    }

    fun answerFeedback(question: HackQuestion, wasCorrect: Boolean): String {
        val prefix = if (wasCorrect) {
            "Correct! "
        } else {
            "Wrong! "
        }
        return prefix + question.explanation
    }

    fun scoreFeedback(score: Int, total: Int): String {
        val ratio = if (total == 0) 0.0 else score.toDouble() / total.toDouble()
        return when {
            ratio >= 0.8 -> "Master Hacker! You can separate useful tips from risky internet myths."
            ratio >= 0.5 -> "Good job! You spotted several myths, but there is still room to sharpen your judgement."
            else -> "Stay Safe Online! Review the explanations and keep practising before trusting every viral tip."
        }
    }

    fun buildReviewText(): String {
        val builder = StringBuilder()

        // The review screen is generated with a loop so that every question is shown consistently.
        for ((index, question) in HackQuestionRepository.questions.withIndex()) {
            val label = if (question.isRealHack) "Hack" else "Myth"
            builder.append("${index + 1}. ${question.statement}\n")
            builder.append("Answer: $label\n")
            builder.append("Explanation: ${question.explanation}\n\n")
        }

        return builder.toString().trim()
    }
}
