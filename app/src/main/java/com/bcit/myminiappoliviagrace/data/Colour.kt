package com.bcit.myminiappoliviagrace.data


import com.google.gson.annotations.SerializedName


/**
 * Olivia Grace, A01363245
 */

/**
 * The Colour class represents a response from the CSS Colors REST API.
 */
data class Colour(
    val status: Int,
    @SerializedName("data")
    val colourInfo: ColourInfo?
)

/**
 * The ColourInfo class represents the data from a CSS Colors REST API response with status 200.
 */
data class ColourInfo(
    val name: String,
    val hex: String,
    val rgb: String
)