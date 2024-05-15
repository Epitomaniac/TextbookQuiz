package com.epitomaniac.textbookquiz.ui.screens

import androidx.compose.foundation.layout.Column
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
  quizViewModel: QuizViewModel = viewModel()
) {
  val uiState by quizViewModel.uiState.collectAsState()
  
  val fontSize = 24.sp
  
  Card(
    modifier = modifier.fillMaxSize(),
  ) {
    Column(
      modifier = Modifier.padding(
        top = 56.dp, start = 16.dp, end = 16.dp
      )
    ) {
      Text(
        text = uiState.question.question,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold
      )
      Spacer(modifier = Modifier.height(16.dp))
      uiState.question.options.forEach { option ->
        val isCorrect = option == uiState.question.answer
        Button(
          onClick = {
            quizViewModel.selectAnswer(option)
          }, modifier = Modifier.fillMaxWidth(), colors = when {
            uiState.selectedAnswer == option && isCorrect -> ButtonDefaults.buttonColors(
              containerColor = Color.Green
            )
            
            uiState.selectedAnswer == option && !isCorrect -> ButtonDefaults.buttonColors(
              containerColor = Color.Red
            )
            
            uiState.selectedAnswer != null && isCorrect -> ButtonDefaults.buttonColors(
              containerColor = Color.Green
            )
            
            else -> ButtonDefaults.buttonColors(containerColor = Color.Gray)
          }
        ) {
          Text(text = option, fontSize = fontSize)
        }
        Spacer(modifier = Modifier.height(8.dp))
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  TextbookQuizTheme {
    QuizScreen()
  }
}