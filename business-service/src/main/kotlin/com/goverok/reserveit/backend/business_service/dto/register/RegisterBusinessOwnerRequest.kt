package com.goverok.reserveit.backend.business_service.dto.register

data class RegisterBusinessOwnerRequest(
    val phoneNumber: String,
    val password: String,
    val businessName: String,
    val businessDescription: String?
)