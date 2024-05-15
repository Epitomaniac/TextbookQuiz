package com.epitomaniac.textbookquiz.ui.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizViewModel : ViewModel() {
  private val _uiState = MutableStateFlow(QuizUiState())
  val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()
  
  fun selectAnswer(option: String) {
    _uiState.value = _uiState.value.copy(selectedAnswer = option)
  }
  
  
}