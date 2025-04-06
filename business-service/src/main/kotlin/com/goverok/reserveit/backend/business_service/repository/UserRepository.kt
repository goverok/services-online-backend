package com.goverok.reserveit.backend.business_service.repository

import com.goverok.reserveit.backend.business_service.model.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    fun existsByPhoneNumber(phoneNumber: String): Boolean
    fun findByPhoneNumber(phoneNumber: String): UserEntity?
}
