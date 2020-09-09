package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SQSApplication

fun main(args: Array<String>) {
    runApplication<SQSApplication>(*args)
}
