package ru.tsu.project.network

import retrofit2.http.*
import ru.tsu.project.network.games.GameDetails
import ru.tsu.project.network.games.GameResult
import ru.tsu.project.network.login.LoginForm
import ru.tsu.project.network.token.TokenResponse

interface Api {

    @POST("auth/login")
    suspend fun login(@Body form: LoginForm): TokenResponse

    @GET("games")
    suspend fun getGames(@Header("Authorization") token: String): List<GameDetails>

    @GET("games/{gameId}/results")
    suspend fun getRank(
        @Path("gameId") gameId: String,
        @Header("Authorization") token: String
    ): List<GameResult>
}