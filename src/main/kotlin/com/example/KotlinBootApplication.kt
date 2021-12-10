package com.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class KotlinBootApplication

fun main(args: Array<String>) {
    SpringApplication.run(KotlinBootApplication::class.java, *args)
}
