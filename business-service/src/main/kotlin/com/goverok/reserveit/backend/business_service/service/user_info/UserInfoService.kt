package com.goverok.reserveit.backend.business_service.service.user_info

import com.goverok.reserveit.backend.business_service.dto.me.BusinessProfileResponse
import com.goverok.reserveit.backend.business_service.dto.me.MeResponse
import com.goverok.reserveit.backend.business_service.model.user.UserEntity
import com.goverok.reserveit.backend.business_service.repository.BusinessProfileRepository
import org.springframework.stereotype.Service

@Service
class UserInfoService(
    private val businessProfileRepository: BusinessProfileRepository
) {

    fun getCurrentUserInfo(user: UserEntity): MeResponse {
        val business = businessProfileRepository.findByOwnerId(user.id)
        val businessDto = business?.let {
            BusinessProfileResponse(
                id = it.id,
                name = it.name,
                description = it.description,
            )
        }

        return MeResponse(
            id = user.id,
            phoneNumber = user.phoneNumber,
            roles = user.roles.map { it.name.name },
            business = businessDto
        )
    }
}