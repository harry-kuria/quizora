package com.example.feature_student.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.QuizAnswer
import com.example.data.repository.StudentQuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentQuizViewModel @Inject constructor(
    private val repository: StudentQuizRepository
): ViewModel() {

    private val _answers = MutableStateFlow<Map<String, QuizAnswer>>(emptyMap())
    val answers: StateFlow<Map<String, QuizAnswer>> = _answers

    fun observeAnswers(quizId: String, studentId: String) {
        repository.observeAnswers(quizId, studentId) { list ->
            _answers.value = list.associateBy { it.quizId }
        }
    }

    fun submitAnswer(answer: QuizAnswer) {
        viewModelScope.launch {
            repository.saveAnswer(answer)
        }
    }
}
