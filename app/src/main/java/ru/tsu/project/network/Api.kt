package ru.tsu.project.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import ru.tsu.project.network.games.GameDetails
import ru.tsu.project.network.login.LoginForm
import ru.tsu.project.network.token.TokenResponse

interface Api {

    @POST("auth/login")
    suspend fun login(@Body form: LoginForm): TokenResponse

    @GET("games")
    suspend fun getGames(@Header("Authorization") token: String): List<GameDetails>
}