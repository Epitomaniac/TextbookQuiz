package com.epitomaniac.textbookquiz.data

import kotlinx.serialization.Serializable

@Serializable
data class Question(
  val id: Int,
  val question: String,
  val options: List<String>,
  val answer: String,
)

