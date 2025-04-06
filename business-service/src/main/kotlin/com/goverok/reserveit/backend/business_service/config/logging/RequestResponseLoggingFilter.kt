package com.goverok.reserveit.backend.business_service.config.logging

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class RequestResponseLoggingFilter : OncePerRequestFilter() {

    private val filterLogger = LoggerFactory.getLogger(this::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val wrappedRequest = CachedBodyHttpServletRequest(request)
        val wrappedResponse = CachedBodyHttpServletResponse(response)

        val requestBody = wrappedRequest.reader.readText()
        filterLogger.info("➡️ Request: ${request.method} ${request.requestURI}")
        filterLogger.info("📦 Request Body: $requestBody")

        filterChain.doFilter(wrappedRequest, wrappedResponse)

        val responseBody = wrappedResponse.getBody()
        filterLogger.info("⬅️ Response status: ${response.status}")
        filterLogger.info("📤 Response Body: $responseBody")

        response.outputStream.write(responseBody.toByteArray())
    }
}