package com.epitomaniac.textbookquiz.data


object QuestionRepository {
  private var questions: List<Question> = emptyList()
  
  fun setQuestions(newQuestions: List<Question>) {
    questions = newQuestions
  }
  
  fun getQuestions(): List<Question> {
    return questions
  }
}