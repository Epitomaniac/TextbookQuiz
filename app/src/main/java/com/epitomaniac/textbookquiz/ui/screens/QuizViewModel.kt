package com.epitomaniac.textbookquiz.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epitomaniac.textbookquiz.data.Question
import com.epitomaniac.textbookquiz.data.QuestionDatabase
import com.epitomaniac.textbookquiz.network.QuestionApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class QuizUiState(
  val remoteString: String = "",
  val questions: List<Question> = emptyList(),
  val currentQuestionIndex: Int = 0,
  val selectedAnswers: Map<Int, String> = emptyMap()
) {
  val currentQuestion: Question?
    get() = questions.getOrNull(currentQuestionIndex)
}

class QuizViewModel : ViewModel() {
  private val _uiState = MutableStateFlow(QuizUiState())
  val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()
  
  private val questionDatabase = QuestionDatabase()
  
  init {
    loadQuestions()
    fetchRemoteString()
  }
  
  private fun loadQuestions() {
    val questions = questionDatabase.loadQuestions()
    _uiState.value = _uiState.value.copy(questions = questions)
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
        Log.e("QuizViewModel", "Error fetching remote string", e)
      }
    }
  }
  
  
  fun selectAnswer(option: String) {
    val currentIndex = _uiState.value.currentQuestionIndex
    val newSelectedAnswers =
      _uiState.value.selectedAnswers.toMutableMap()
    newSelectedAnswers[currentIndex] = option
    _uiState.value =
      _uiState.value.copy(selectedAnswers = newSelectedAnswers)
  }
  
  fun nextQuestion() {
    val nextIndex =
      (_uiState.value.currentQuestionIndex + 1).coerceAtMost(
        _uiState.value.questions.size - 1
      )
    _uiState.value =
      _uiState.value.copy(currentQuestionIndex = nextIndex)
  }
  
  fun previousQuestion() {
    val previousIndex =
      (_uiState.value.currentQuestionIndex - 1).coerceAtLeast(0)
    _uiState.value =
      _uiState.value.copy(currentQuestionIndex = previousIndex)
  }
  
}