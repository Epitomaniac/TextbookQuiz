package com.epitomaniac.textbookquiz.data

data class Question constructor(
  val question: String,
  val options: List<String>,
  val answer: String
)