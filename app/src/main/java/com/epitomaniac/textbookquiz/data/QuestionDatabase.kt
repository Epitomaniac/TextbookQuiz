package com.epitomaniac.textbookquiz.data

data class Question constructor(
  val question: String,
  val options: List<String>,
  val answer: String
)

class QuestionDatabase() {
  fun loadQuestions(): List<Question> {
    return listOf<Question>(
      Question(
        question = "What is the capital of France? ",
        options = listOf("Paris", "London", "Berlin", "Madrid"),
        answer = "Paris"
      ),
      Question(
        question = "What color is a banana? ",
        options = listOf("red", "blue", "green", "yellow"),
        answer = "yellow"
      ),
      Question(
        question = "Which one is not a land vehicle? ",
        options = listOf("a truck", "a car", "a bike", "a boat"),
        answer = "a boat"
      ),
    )
  }
}
