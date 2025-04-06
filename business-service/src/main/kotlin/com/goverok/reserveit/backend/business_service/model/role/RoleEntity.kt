package com.goverok.reserveit.backend.business_service.model.role

import jakarta.persistence.*

@Entity
@Table(name = "roles")
data class RoleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    val name: RoleName
)

enum class RoleName {
    BUSINESS_OWNER,
    EMPLOYEE
}