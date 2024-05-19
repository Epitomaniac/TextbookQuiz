package com.epitomaniac.textbookquiz.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.epitomaniac.textbookquiz.data.theme.TextbookQuizTheme

@Composable
fun QuizScreen(
  modifier: Modifier = Modifier,
  quizViewModel: QuizViewModel = viewModel(),
) {
  val uiState by quizViewModel.uiState.collectAsState()
  val fontSize = 24.sp
  
  Card(
    modifier = modifier.fillMaxSize()
  ) {
    Column(
      modifier = Modifier.padding(
        top = 56.dp, start = 16.dp, end = 16.dp
      )
    ) {
      uiState.currentQuestion?.let { question ->
        Text(
          text = question.question,
          fontSize = 28.sp,
          lineHeight = 36.sp,
          fontWeight = FontWeight.Bold,
          modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(36.dp))
        question.options.forEach { option ->
          val isCorrect = option == question.answer
          Button(
            onClick = {
              quizViewModel.selectAnswer(option)
            }, modifier = Modifier.fillMaxWidth(), colors = when {
              uiState.selectedAnswers[uiState.currentQuestionIndex] == option && isCorrect -> ButtonDefaults.buttonColors(
                containerColor = Color.Green
              )
              
              uiState.selectedAnswers[uiState.currentQuestionIndex] == option && !isCorrect -> ButtonDefaults.buttonColors(
                containerColor = Color.Red
              )
              
              uiState.selectedAnswers[uiState.currentQuestionIndex] != null && isCorrect -> ButtonDefaults.buttonColors(
                containerColor = Color.Green
              )
              
              else -> ButtonDefaults.buttonColors(containerColor = Color.Gray)
            }
          ) {
            Text(text = option, fontSize = fontSize)
          }
          Spacer(modifier = Modifier.height(20.dp))
        }
        Spacer(modifier = Modifier.height(36.dp))
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Button(onClick = { quizViewModel.previousQuestion() }) {
            Text(text = "Previous")
          }
          Button(onClick = { quizViewModel.nextQuestion() }) {
            Text(text = "Next")
          }
        }
      }
    }
  }
}


@Preview(showBackground = true)
@Composable
private fun PagePreview() {
  TextbookQuizTheme {
    QuizScreen()
  }
}