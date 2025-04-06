package com.goverok.reserveit.backend.business_service.controller

import com.goverok.reserveit.backend.business_service.dto.login.LoginRequest
import com.goverok.reserveit.backend.business_service.dto.login.LoginResponse
import com.goverok.reserveit.backend.business_service.dto.register.RegisterBusinessOwnerRequest
import com.goverok.reserveit.backend.business_service.dto.register.RegisterBusinessOwnerResponse
import com.goverok.reserveit.backend.business_service.service.login.BusinessLoginService
import com.goverok.reserveit.backend.business_service.service.register.BusinessRegistrationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/business/auth")
class BusinessAuthController(
    private val registrationService: BusinessRegistrationService,
    private val loginService: BusinessLoginService,
) {

    @PostMapping("/register")
    fun registerOwner(@RequestBody request: RegisterBusinessOwnerRequest): ResponseEntity<RegisterBusinessOwnerResponse> {
        val response = registrationService.registerOwner(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        val token = loginService.login(request.phoneNumber, request.password)
        return ResponseEntity.ok(LoginResponse(token))
    }
}