package com.example.feature_teacher.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.Quiz
import com.example.data.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val repository: QuizRepository
) : ViewModel() {

    val quizList: StateFlow<List<Quiz>> = repository.quizList.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    init {
        repository.observeQuizzes()
    }

    fun addQuiz(quiz: Quiz) {
        viewModelScope.launch {
            repository.addQuiz(quiz)
        }
    }
}
