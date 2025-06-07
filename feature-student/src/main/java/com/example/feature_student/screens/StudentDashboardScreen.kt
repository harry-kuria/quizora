package com.example.feature_student.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.model.Quiz
import com.example.feature_teacher.viewmodel.QuizViewModel

@Composable
fun StudentDashboardScreen(
    viewModel: QuizViewModel = hiltViewModel(), // assumes you have quizList
    onQuizSelected: (Quiz) -> Unit
) {
    val quizList by viewModel.quizList.collectAsState(initial = emptyList())

    LazyColumn {
        items(quizList) { quiz ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onQuizSelected(quiz) }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = quiz.question, style = MaterialTheme.typography.titleMedium)
                    Text(text = "Options: ${quiz.options.size}")
                }
            }
        }
    }
}
