package com.epitomaniac.textbookquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.epitomaniac.textbookquiz.data.Question
import com.epitomaniac.textbookquiz.ui.theme.TextbookQuizTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      TextbookQuizTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
          QuizScreen()
        }
      }
    }
  }
}

@Composable
fun QuizScreen(modifier: Modifier = Modifier) {
  val question = Question(
    question = "What is the capital of France?",
    options = listOf("London", "Paris", "Rome", "Munich"),
    answer = "Paris"
  )
  val fontSize = 24.sp
  
  var selectedAnswer by remember { mutableStateOf<String?>(null) }
  
  Card(
    modifier = modifier.fillMaxSize(),
  ) {
    Column(
      modifier = Modifier.padding(
        top = 56.dp, start = 16.dp, end = 16.dp
      )
    ) {
      Text(
        text = question.question,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold
      )
      Spacer(modifier = Modifier.height(16.dp))
      question.options.forEach { option ->
        val isCorrect = option == question.answer
        Button(
          onClick = {
            selectedAnswer = option
          }, modifier = modifier.fillMaxWidth(),
          colors = when {
            selectedAnswer == option && isCorrect -> ButtonDefaults.buttonColors(containerColor = Color.Green)
            selectedAnswer == option && !isCorrect -> ButtonDefaults.buttonColors(containerColor = Color.Red)
            selectedAnswer != null && isCorrect -> ButtonDefaults.buttonColors(containerColor = Color.Green)
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