package com.example.core.mappers

import com.example.core.model.Quiz
import com.example.core.model.QuizAnswer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

fun Quiz.toJson(): String {
    return Json.encodeToString(this)
}

fun String.toQuiz(): Quiz? {
    return try {
        Json.decodeFromString(this)
    } catch (e: Exception) {
        null
    }
}

fun QuizAnswer.toJson(): String = Json.encodeToString(serializer(), this)
fun String.toQuizAnswer(): QuizAnswer = Json.decodeFromString(serializer(), this)
