package com.epitomaniac.textbookquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
  class Question constructor(
    val question: String,
    val options: List<String>,
    val answer: String
  )
  
  val fontSize = 24.sp
  val sampleQuestion = Question(
    question = "What is the capital of France?",
    options = listOf("London", "Paris", "Rome", "Munich"),
    answer = "Paris"
  )
  
  // State to track the selected answer
  var selectedAnswer by remember { mutableStateOf<String?>(null) }
  
  // Function to handle answer selection
  fun onAnswerSelected(option: String) {
    selectedAnswer = option
  }
  
  Card(
    modifier = modifier.fillMaxSize(),
  ) {
    Column(
      modifier = modifier.padding(16.dp)
    ) {
      Text(
        text = sampleQuestion.question,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(bottom = 40.dp)
      )
      
      sampleQuestion.options.forEach { option ->
        val backgroundColor = when {
          selectedAnswer == null -> Color(0xFF6200EE)
          option == sampleQuestion.answer -> Color.Green
          option == selectedAnswer -> Color.Red
          else -> Color(0xFF6200EE)
        }
        
        Button(
          onClick = { onAnswerSelected(option) },
          modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
          colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
          )
        ) {
          Text(
            text = option,
            fontSize = fontSize,
            color = Color.White
          )
        }
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