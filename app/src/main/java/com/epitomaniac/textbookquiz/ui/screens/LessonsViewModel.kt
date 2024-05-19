package com.epitomaniac.textbookquiz.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epitomaniac.textbookquiz.data.Question
import com.epitomaniac.textbookquiz.data.QuestionRepository
import com.epitomaniac.textbookquiz.network.QuestionApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class LessonsUiState(
  val remoteData: List<Question>? = null,
)

class LessonsViewModel : ViewModel() {
  private val _uiState = MutableStateFlow(LessonsUiState())
  val uiState: StateFlow<LessonsUiState> = _uiState.asStateFlow()
  
  init {
    fetchRemoteData()
  }
  
  private fun fetchRemoteData() {
    viewModelScope.launch {
      try {
        val remoteData = QuestionApi.retrofitService.getQuestions()
        _uiState.value = _uiState.value.copy(remoteData = remoteData)
        QuestionRepository.setQuestions(remoteData) // Store in a shared repository
      } catch (e: Exception) {
        Log.e("LessonsViewModel", "Error fetching remote data", e)
      }
    }
  }
}