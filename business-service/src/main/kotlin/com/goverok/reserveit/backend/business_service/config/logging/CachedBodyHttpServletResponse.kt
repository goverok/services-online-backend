package com.goverok.reserveit.backend.business_service.config.logging

import jakarta.servlet.ServletOutputStream
import jakarta.servlet.WriteListener
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpServletResponseWrapper
import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets

class CachedBodyHttpServletResponse(response: HttpServletResponse) : HttpServletResponseWrapper(response) {
    private val outputStream = ByteArrayOutputStream()

    override fun getOutputStream() = object : ServletOutputStream() {
        override fun write(b: Int) = outputStream.write(b)
        override fun isReady() = true
        override fun setWriteListener(writeListener: WriteListener?) {}
    }

    fun getBody(): String = outputStream.toString(StandardCharsets.UTF_8.name())
}