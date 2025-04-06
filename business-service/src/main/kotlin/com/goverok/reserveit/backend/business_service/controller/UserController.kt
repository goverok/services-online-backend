package com.goverok.reserveit.backend.business_service.controller

import com.goverok.reserveit.backend.business_service.dto.me.MeResponse
import com.goverok.reserveit.backend.business_service.model.user.UserEntity
import com.goverok.reserveit.backend.business_service.service.user_info.UserInfoService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/business/user")
class UserController(
    private val userInfoService: UserInfoService,
) {

    @GetMapping("/me")
    fun getMe(@AuthenticationPrincipal user: UserEntity): ResponseEntity<MeResponse> {
        val me = userInfoService.getCurrentUserInfo(user)
        return ResponseEntity.ok(me)
    }
}