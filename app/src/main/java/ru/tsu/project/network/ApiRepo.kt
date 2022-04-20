package ru.tsu.project.network

import ru.tsu.project.network.login.LoginForm

class ApiRepo(val api: Api) {

    suspend fun login(form: LoginForm) = api.login(form)

    suspend fun getGames() = api.getGames("Bearer ${Network.token.accessToken.token}")
}