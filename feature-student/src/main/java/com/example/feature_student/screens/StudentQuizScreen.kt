package com.example.feature_student.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.model.Quiz
import com.example.core.model.QuizAnswer
import com.example.feature_student.viewmodel.StudentQuizViewModel

@Composable
fun StudentQuizScreen(
    quiz: Quiz,
    studentId: String,
    viewModel: StudentQuizViewModel = hiltViewModel()
) {
    val answers by viewModel.answers.collectAsState()

    LaunchedEffect(quiz.id, studentId) {
        viewModel.observeAnswers(quiz.id, studentId)
    }

    val selectedAnswer = answers[quiz.id]?.selectedIndex

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = quiz.question, style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))

        quiz.options.forEachIndexed { index, option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val answer = QuizAnswer(
                            studentId = studentId,
                            quizId = quiz.id,
                            selectedIndex = index,
                            timestamp = System.currentTimeMillis()
                        )
                        viewModel.submitAnswer(answer)
                    }
                    .padding(8.dp)
            ) {
                RadioButton(
                    selected = selectedAnswer == index,
                    onClick = null
                )
                Text(text = option, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}
