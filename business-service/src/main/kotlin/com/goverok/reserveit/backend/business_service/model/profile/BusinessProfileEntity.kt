package com.goverok.reserveit.backend.business_service.model.profile

import com.goverok.reserveit.backend.business_service.model.user.UserEntity
import jakarta.persistence.*

@Entity
@Table(name = "business_profiles")
data class BusinessProfileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = true)
    val description: String? = null,

    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false, unique = true)
    val owner: UserEntity
) {

    override fun toString(): String {
        return "BusinessProfileEntity(id=$id, name='$name', description='$description')"
    }

}