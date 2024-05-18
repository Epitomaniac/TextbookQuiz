package com.epitomaniac.textbookquiz.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://textbookquiz.runflare.run/api/"

private val retrofit = Retrofit.Builder()
  .addConverterFactory(ScalarsConverterFactory.create())
  .baseUrl(BASE_URL).build()


interface QuestionApiService {
  @GET("questions")
  suspend fun getQuestions(): String
  
}

object QuestionApi {
  val retrofitService: QuestionApiService by lazy {
    retrofit.create(QuestionApiService::class.java)
  }
}