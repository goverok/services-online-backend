package com.goverok.reserveit.backend.business_service.dto.login

data class LoginRequest(
    val phoneNumber: String,
    val password: String
)

data class LoginResponse(
    val token: String
)