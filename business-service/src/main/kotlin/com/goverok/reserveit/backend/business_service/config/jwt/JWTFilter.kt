package com.goverok.reserveit.backend.business_service.config.jwt

import com.goverok.reserveit.backend.business_service.repository.UserRepository
import com.goverok.reserveit.backend.business_service.security.JWTService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JWTFilter(
    private val jwtService: JWTService,
    private val userRepository: UserRepository
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val token = authHeader.removePrefix("Bearer ").trim()

            if (jwtService.isValidToken(token)) {
                val phoneNumber = jwtService.getPhoneNumber(token)
                val user = userRepository.findByPhoneNumber(phoneNumber)

                if (user != null) {
                    val authorities = user.roles.map { SimpleGrantedAuthority(it.name.name) }
                    val authentication = UsernamePasswordAuthenticationToken(user, null, authorities)
                    SecurityContextHolder.getContext().authentication = authentication
                }
            }
        }

        filterChain.doFilter(request, response)
    }
}