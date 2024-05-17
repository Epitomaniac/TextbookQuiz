package com.epitomaniac.textbookquiz.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epitomaniac.textbookquiz.network.QuestionApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class LessonsUiState(
  val remoteString: String = "",
)

class LessonsViewModel : ViewModel() {
  private val _uiState = MutableStateFlow(LessonsUiState())
  val uiState: StateFlow<LessonsUiState> = _uiState.asStateFlow()
  
  init {
    fetchRemoteString()
  }
  
  private fun fetchRemoteString() {
    viewModelScope.launch {
      try {
        val remoteString = QuestionApi.retrofitService.getQuestions()
        _uiState.value =
          _uiState.value.copy(remoteString = remoteString)
      } catch (e: Exception) {
        _uiState.value =
          _uiState.value.copy(remoteString = "error occurred")
        Log.e("LessonsViewModel", "Error fetching remote string", e)
      }
    }
  }
}