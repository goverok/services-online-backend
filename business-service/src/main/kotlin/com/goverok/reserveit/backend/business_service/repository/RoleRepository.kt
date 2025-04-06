package com.goverok.reserveit.backend.business_service.repository

import com.goverok.reserveit.backend.business_service.model.role.RoleEntity
import com.goverok.reserveit.backend.business_service.model.role.RoleName
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<RoleEntity, Long> {
    fun findByName(name: RoleName): RoleEntity?
}