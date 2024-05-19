package com.epitomaniac.textbookquiz.ui.screens

import androidx.lifecycle.ViewModel
import com.epitomaniac.textbookquiz.data.Question
import com.epitomaniac.textbookquiz.data.QuestionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class QuizUiState(
  val questions: List<Question> = emptyList(),
  val currentQuestionIndex: Int = 0,
  val selectedAnswers: Map<Int, String> = emptyMap(),
) {
  val currentQuestion: Question?
    get() = questions.getOrNull(currentQuestionIndex)
}

class QuizViewModel : ViewModel() {
  private val _uiState = MutableStateFlow(QuizUiState())
  val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()
  
  init {
    loadQuestions()
  }
  
  private fun loadQuestions() {
    val questions = QuestionRepository.getQuestions()
    _uiState.value = _uiState.value.copy(questions = questions)
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