package com.epitomaniac.textbookquiz.ui.screens

import com.epitomaniac.textbookquiz.data.Question

data class QuizUiState(
  val question: Question = Question(
    question = "What is the capital of France?",
    options = listOf("London", "Paris", "Madrid", "Berlin"),
    answer = "Paris"
  ),
  val selectedAnswer: String? = null
)