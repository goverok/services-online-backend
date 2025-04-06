package com.goverok.reserveit.backend.business_service.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin")
class AdminController {
    @GetMapping("/ping")
    fun ping(): Pong = Pong("pong")
}

data class Pong(
    val value: String,
)