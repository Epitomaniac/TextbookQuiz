package com.epitomaniac.textbookquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.epitomaniac.textbookquiz.ui.screens.QuizScreen
import com.epitomaniac.textbookquiz.data.theme.TextbookQuizTheme

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

