package com.goverok.reserveit.backend.business_service.security

import com.goverok.reserveit.backend.business_service.model.user.UserEntity
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class JWTService(
    @Value("\${jwt.secret}") private val secret: String,
    @Value("\${jwt.expiration}") private val expirationMs: Long
) {

    private val key = Keys.hmacShaKeyFor(secret.toByteArray())

    fun generateToken(user: UserEntity): String {
        val claims = Jwts.claims().apply {
            put("userId", user.id)
            put("roles", user.roles.map { it.name.name })
        }

        val now = Date()
        val expiry = Date(now.time + expirationMs)

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(user.phoneNumber)
            .setIssuedAt(now)
            .setExpiration(expiry)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun getPhoneNumber(token: String): String =
        Jwts.parserBuilder().setSigningKey(key).build()
            .parseClaimsJws(token)
            .body
            .subject

    fun isValidToken(token: String): Boolean = try {
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
        true
    } catch (e: Exception) {
        false
    }
}