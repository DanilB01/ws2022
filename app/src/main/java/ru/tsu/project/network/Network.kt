package ru.tsu.project.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.tsu.project.network.token.TokenResponse

object Network {
    lateinit var token: TokenResponse
    lateinit var gameId: String

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://62.113.103.113/api/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(client)
        .build()
        .create(Api::class.java)
}