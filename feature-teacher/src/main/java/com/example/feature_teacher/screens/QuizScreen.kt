package com.example.feature_teacher.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.model.Quiz
import com.example.feature_teacher.viewmodel.QuizViewModel
import java.util.UUID
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun QuizScreen(viewModel: QuizViewModel = hiltViewModel()) {
    val quizList by viewModel.quizList.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = {
            val newQuiz = Quiz(
                id = UUID.randomUUID().toString(),
                question = "What is the capital of Kenya?",
                options = listOf("Nairobi", "Mombasa", "Kisumu"),
                correctIndex = 0,
                createdAt = System.currentTimeMillis()
            )
            viewModel.addQuiz(newQuiz)
        }) {
            Text("Add Sample Quiz")
        }

        Spacer(Modifier.height(16.dp))

        Text("Live Quizzes:", style = MaterialTheme.typography.titleMedium)

        quizList.forEach { quiz ->
            Text("- ${quiz.question}")
        }
    }
}
