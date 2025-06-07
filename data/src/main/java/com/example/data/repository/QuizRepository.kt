package com.example.data.repository

import com.example.core.mappers.toQuiz
import com.example.core.model.Quiz
import live.ditto.Ditto
import live.ditto.DittoCollection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class QuizRepository(private val ditto: Ditto) {

    private val quizzes: DittoCollection = ditto.store["quizzes"]
    private val _quizList = MutableStateFlow<List<Quiz>>(emptyList())
    val quizList: Flow<List<Quiz>> get() = _quizList
private val QUIZZES_QUERY: String = "SELECT * FROM quizzes"

    fun observeQuizzes() {
        ditto.store.registerObserver(QUIZZES_QUERY) {result ->
            _quizList.value = result.items.mapNotNull { it.value.toString().toQuiz() }
        }


    }

    suspend fun addQuiz(quiz: Quiz) {
        ditto.store.execute(
            "INSERT INTO quizzes DOCUMENTS (:quiz)",
            mapOf("quiz" to quiz))
    }
}
