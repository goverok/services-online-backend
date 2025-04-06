package com.goverok.reserveit.backend.business_service.model.user

import com.goverok.reserveit.backend.business_service.model.profile.BusinessProfileEntity
import com.goverok.reserveit.backend.business_service.model.role.RoleEntity
import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "phone_number", nullable = false, unique = true)
    val phoneNumber: String,

    @Column(nullable = false)
    val password: String,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val roles: Set<RoleEntity> = setOf(),

    @OneToOne(mappedBy = "owner", cascade = [CascadeType.ALL])
    val businessProfile: BusinessProfileEntity? = null
) {

    override fun toString(): String {
        return "UserEntity(id=$id, phoneNumber='$phoneNumber', roles=${roles.map { it.name }})"
    }

}