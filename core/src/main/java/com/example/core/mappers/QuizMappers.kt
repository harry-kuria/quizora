package com.example.core.mappers

import com.example.core.model.Quiz
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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