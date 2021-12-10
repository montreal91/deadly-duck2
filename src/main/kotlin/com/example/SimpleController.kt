package com.example

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kotlin")
class SimpleController {
    @GetMapping("/hello/")
    fun defaultGreeting(): String {
        return "Hello, World!"
    }

    @GetMapping("/hello/{name}/")
    fun namedGreeting(@PathVariable name: String): String {
        return "Hello, $name!"
    }
}
