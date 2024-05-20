package com.epitomaniac.textbookquiz.network

import com.epitomaniac.textbookquiz.data.Question
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import java.security.cert.CertificateException
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

private const val BASE_URL = "https://textquiz.ir/api/"

private val json = Json { ignoreUnknownKeys = true }

private fun getUnsafeOkHttpClient(): OkHttpClient {
  val trustAllCertificates =
    arrayOf<TrustManager>(object : X509TrustManager {
      @Throws(CertificateException::class)
      override fun checkClientTrusted(
        chain: Array<java.security.cert.X509Certificate>,
        authType: String,
      ) { // Trust all client certificates
      }
      
      @Throws(CertificateException::class)
      override fun checkServerTrusted(
        chain: Array<java.security.cert.X509Certificate>,
        authType: String,
      ) { // Trust all server certificates
      }
      
      override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> =
        arrayOf()
    })
  
  val sslContext = SSLContext.getInstance("SSL")
  sslContext.init(
    null,
    trustAllCertificates,
    java.security.SecureRandom()
  )
  
  val sslSocketFactory = sslContext.socketFactory
  
  return OkHttpClient.Builder().sslSocketFactory(
      sslSocketFactory,
      trustAllCertificates[0] as X509TrustManager
    ).hostnameVerifier(HostnameVerifier { _, _ -> true })
    .addInterceptor(HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }).build()
}

private val retrofit = Retrofit.Builder()
  .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
  .client(getUnsafeOkHttpClient()).baseUrl(BASE_URL).build()

interface QuestionApiService {
  @GET("questions")
  suspend fun getQuestions(): List<Question>
}

object QuestionApi {
  val retrofitService: QuestionApiService by lazy {
    retrofit.create(QuestionApiService::class.java)
  }
}
