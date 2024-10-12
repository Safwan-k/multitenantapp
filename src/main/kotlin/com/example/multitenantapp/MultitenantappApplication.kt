package com.example.multitenantapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MultitenantappApplication

fun main(args: Array<String>) {
	runApplication<MultitenantappApplication>(*args)
}
