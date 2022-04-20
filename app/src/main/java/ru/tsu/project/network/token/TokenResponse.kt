package ru.tsu.project.network.token

data class TokenResponse(
    val accessToken: TokenDto,
    val refreshToken: TokenDto
)

data class TokenDto(
    val token: String,
    val expiresIn: Int
)
