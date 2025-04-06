package com.goverok.reserveit.backend.business_service.dto.me

data class MeResponse(
    val id: Long,
    val phoneNumber: String,
    val roles: List<String>,
    val business: BusinessProfileResponse?
)

data class BusinessProfileResponse(
    val id: Long,
    val name: String,
    val description: String?,
)