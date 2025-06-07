package com.example.data.repository

import com.example.core.mappers.toJson
import com.example.core.mappers.toQuizAnswer
import com.example.core.model.QuizAnswer
import live.ditto.Ditto
import live.ditto.DittoCollection

class StudentQuizRepository(private val ditto: Ditto) {

    private val answers: DittoCollection = ditto.store["quizAnswers"]

    fun observeAnswers(quizId: String, studentId: String, onChange: (List<QuizAnswer>) -> Unit) {
        val query = "SELECT * FROM quizAnswers WHERE quizId = '$quizId' AND studentId = '$studentId'"
        ditto.store.registerObserver(query) { result ->
            val list = result.items.mapNotNull { it.value?.toString()?.toQuizAnswer() }
            onChange(list)
        }
    }

    suspend fun saveAnswer(answer: QuizAnswer) {
        ditto.store.execute(
            "INSERT INTO quizAnswers DOCUMENTS (:answer)",
            mapOf("answer" to answer)
        )
    }

}
