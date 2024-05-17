package com.epitomaniac.textbookquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.epitomaniac.textbookquiz.data.theme.TextbookQuizTheme
import com.epitomaniac.textbookquiz.ui.screens.LessonsScreen
import com.epitomaniac.textbookquiz.ui.screens.QuizScreen

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      TextbookQuizTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
          TextBookQuizApp()
        }
      }
    }
  }
}

@Suppress("unused")
enum class QuizAppScreen(val title: String) {
  LessonsPage("Lessons"), QuizPage("Quiz")
}

@Composable
fun TextBookQuizApp() {
  val navController = rememberNavController()
  NavHost(
    navController = navController,
    startDestination = QuizAppScreen.LessonsPage.name,
  ) {
    composable(route = QuizAppScreen.LessonsPage.name) {
      LessonsScreen()
    }
    composable(route = QuizAppScreen.QuizPage.name) {
      QuizScreen()
    }
  }
}



