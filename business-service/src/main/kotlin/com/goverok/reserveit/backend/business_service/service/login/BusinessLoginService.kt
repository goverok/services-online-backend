package com.goverok.reserveit.backend.business_service.service.login

import com.goverok.reserveit.backend.business_service.repository.UserRepository
import com.goverok.reserveit.backend.business_service.security.JWTService
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class BusinessLoginService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JWTService
) {

    fun login(phoneNumber: String, rawPassword: String): String {
        val user = userRepository.findByPhoneNumber(phoneNumber)
            ?: throw UsernameNotFoundException("User not found")

        if (!passwordEncoder.matches(rawPassword, user.password)) {
            throw BadCredentialsException("Invalid credentials")
        }

        return jwtService.generateToken(user)
    }
}