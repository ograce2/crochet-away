package com.bcit.myminiappoliviagrace.data

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get


/**
 * Olivia Grace, A01363245
 */


/**
 * The ColourRepository class contains a function used to make an HTTP request to the CSS Colors
 * REST API.
 */
class ColourRepository(private val httpClient: HttpClient){

    /**
     * Makes a request to the CSS Colors REST API and returns a Colour object that is created
     * from that request.
     */
    suspend fun getColour(colourName : String): Colour{

        // get request
        val response = httpClient.get("$BASE_URL$colourName") // get function is a suspend function

        Log.i("colour", response.toString())

        // retrieve json object as a string
        val json = response.body<JsonObject>().toString()

        Log.i("colour", json)

        // deserialize that object into an Art object
        return Gson().fromJson(json, Colour::class.java)
    }
}