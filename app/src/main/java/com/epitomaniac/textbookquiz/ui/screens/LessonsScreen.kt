package com.epitomaniac.textbookquiz.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.epitomaniac.textbookquiz.data.theme.TextbookQuizTheme


@Composable
fun LessonsScreen(
  onNextButtonClicked: () -> Unit,
  lessonsViewModel: LessonsViewModel = viewModel(),
) {
  val uiState by lessonsViewModel.uiState.collectAsState()
  
  Column(
    modifier = Modifier
      .fillMaxSize().padding(top = 36.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = "Lessons Screen",
      fontSize = 36.sp,
    )
    Button(
      onClick = {
        uiState.remoteData?.let {
          onNextButtonClicked()
        }
      },
      modifier = Modifier.padding(24.dp)
    ) { Text("Lesson 1") }
  }
}

@Preview(showBackground = true)
@Composable
private fun PagePreview() {
  TextbookQuizTheme {
    LessonsScreen(onNextButtonClicked = {})
  }
}