package com.goverok.reserveit.backend.business_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BusinessServiceApplication

fun main(args: Array<String>) {
    println("SPRING_DATASOURCE_URL: ${System.getenv("SPRING_DATASOURCE_URL")}")

    runApplication<BusinessServiceApplication>(*args)
}
