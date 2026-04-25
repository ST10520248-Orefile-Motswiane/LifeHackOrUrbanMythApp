package za.iie.st10520248.lifehackorurbanmyth

object HackQuestionRepository {

    // The fixed question set keeps the quiz predictable for marking and review.
    val questions = listOf(
        HackQuestion(
            statement = "Switching your phone to airplane mode while charging can help it charge faster.",
            isRealHack = true,
            explanation = "Hack: airplane mode reduces background activity, which can slightly improve charging speed."
        ),
        HackQuestion(
            statement = "Putting a wet phone into a bowl of rice is the best repair method.",
            isRealHack = false,
            explanation = "Myth: rice is unreliable and can leave dust behind; powering the phone off and getting proper drying help is safer."
        ),
        HackQuestion(
            statement = "Using a binder clip to keep charging cables in place can reduce desk clutter.",
            isRealHack = true,
            explanation = "Hack: it is a simple physical organiser that keeps cables accessible and tidy."
        ),
        HackQuestion(
            statement = "Dark mode doubles battery life on every phone screen.",
            isRealHack = false,
            explanation = "Myth: power savings depend on the display type and usage, so the effect is not universal or that large."
        ),
        HackQuestion(
            statement = "The two-minute rule can help you finish very small tasks before they pile up.",
            isRealHack = true,
            explanation = "Hack: doing short tasks immediately can reduce procrastination and mental clutter."
        ),
        HackQuestion(
            statement = "Freezing batteries always makes them last longer in everyday devices.",
            isRealHack = false,
            explanation = "Myth: that advice is outdated and can even damage batteries or reduce performance."
        )
    )
}
