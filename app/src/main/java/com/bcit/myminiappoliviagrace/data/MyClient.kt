package com.bcit.myminiappoliviagrace.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson


/**
 * Olivia Grace, A01363245
 */


/**
 * The entry point for creating HTTP requests
 */
val client = HttpClient{

    // install the gson serializer into the ktor client
    install(ContentNegotiation){
        gson()
    }
}