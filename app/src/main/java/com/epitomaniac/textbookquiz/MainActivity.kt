package com.epitomaniac.textbookquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
  val fontSize = 24.sp
  val sampleQuestion = Question(
    question = "What is the capital of France?",
    options = listOf("London", "Paris", "Rome", "Munich"),
    answer = "Paris",
  )
  Column(
    modifier = modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Text(
      text = sampleQuestion.question, fontSize = fontSize
    )
    Spacer(modifier = modifier.height(40.dp))
    Text(text = sampleQuestion.options[0], fontSize = fontSize)
    Text(text = sampleQuestion.options[1], fontSize = fontSize)
    Text(text = sampleQuestion.options[2], fontSize = fontSize)
    Text(text = sampleQuestion.options[3], fontSize = fontSize)
  }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  TextbookQuizTheme {
    QuizScreen()
  }
}