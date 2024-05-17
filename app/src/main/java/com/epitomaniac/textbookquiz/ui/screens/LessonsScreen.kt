package com.epitomaniac.textbookquiz.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.epitomaniac.textbookquiz.data.theme.TextbookQuizTheme


@Composable
fun LessonsScreen() {
  Box(
    modifier = Modifier.fillMaxSize()
  ) {
    Text(
      text = "Lessons Screen",
      fontSize = 36.sp,
      modifier = Modifier.align(Alignment.Center)
    )
  }
}


@Preview(showBackground = true)
@Composable
private fun PagePreview() {
  TextbookQuizTheme {
    LessonsScreen()
  }
}