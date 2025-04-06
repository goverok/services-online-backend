package com.goverok.reserveit.backend.business_service.config.logging

import jakarta.servlet.ReadListener
import jakarta.servlet.ServletInputStream
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

class CachedBodyHttpServletRequest(request: HttpServletRequest) : HttpServletRequestWrapper(request) {
    private val cachedBody: ByteArray = request.inputStream.readBytes()

    override fun getInputStream(): ServletInputStream {
        val byteArrayInputStream = ByteArrayInputStream(cachedBody)
        return object : ServletInputStream() {
            override fun read() = byteArrayInputStream.read()
            override fun isFinished() = byteArrayInputStream.available() == 0
            override fun isReady() = true
            override fun setReadListener(readListener: ReadListener) {}
        }
    }

    override fun getReader(): BufferedReader {
        return BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))
    }
}