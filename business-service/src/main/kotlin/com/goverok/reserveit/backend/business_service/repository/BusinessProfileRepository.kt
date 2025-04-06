package com.goverok.reserveit.backend.business_service.repository

import com.goverok.reserveit.backend.business_service.model.profile.BusinessProfileEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BusinessProfileRepository : JpaRepository<BusinessProfileEntity, Long> {
    fun findByOwnerId(ownerId: Long): BusinessProfileEntity?
}