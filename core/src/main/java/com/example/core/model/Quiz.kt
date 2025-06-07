package com.example.core.model

data class Quiz(
    val id: String,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val createdAt: Long
)