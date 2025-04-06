package com.goverok.reserveit.backend.business_service.service.register

import com.goverok.reserveit.backend.business_service.dto.register.RegisterBusinessOwnerRequest
import com.goverok.reserveit.backend.business_service.dto.register.RegisterBusinessOwnerResponse
import com.goverok.reserveit.backend.business_service.model.profile.BusinessProfileEntity
import com.goverok.reserveit.backend.business_service.model.role.RoleName
import com.goverok.reserveit.backend.business_service.model.user.UserEntity
import com.goverok.reserveit.backend.business_service.repository.BusinessProfileRepository
import com.goverok.reserveit.backend.business_service.repository.RoleRepository
import com.goverok.reserveit.backend.business_service.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class BusinessRegistrationService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val businessProfileRepository: BusinessProfileRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun registerOwner(request: RegisterBusinessOwnerRequest): RegisterBusinessOwnerResponse {
        if (userRepository.existsByPhoneNumber(request.phoneNumber)) {
            throw IllegalArgumentException("User with this phone number already exists")
        }

        val role = roleRepository.findByName(RoleName.BUSINESS_OWNER)
            ?: throw IllegalStateException("Role BUSINESS_OWNER not found")

        val encodedPassword = passwordEncoder.encode(request.password)

        val user = UserEntity(
            phoneNumber = request.phoneNumber,
            password = encodedPassword,
            roles = setOf(role)
        )

        val savedUser = userRepository.save(user)

        val businessProfile = BusinessProfileEntity(
            name = request.businessName,
            description = request.businessDescription,
            owner = savedUser
        )

        val savedBusiness = businessProfileRepository.save(businessProfile)

        return RegisterBusinessOwnerResponse(
            userId = savedUser.id,
            businessId = savedBusiness.id
        )
    }
}