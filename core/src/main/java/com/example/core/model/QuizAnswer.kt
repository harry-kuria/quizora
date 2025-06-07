package com.example.core.model

data class QuizAnswer(
    val studentId: String,
    val quizId: String,
    val selectedIndex: Int,
    val timestamp: Long
)