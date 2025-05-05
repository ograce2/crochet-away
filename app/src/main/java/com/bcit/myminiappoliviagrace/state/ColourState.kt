package com.bcit.myminiappoliviagrace.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import com.bcit.myminiappoliviagrace.data.Colour
import com.bcit.myminiappoliviagrace.data.ColourRepository
import com.bcit.myminiappoliviagrace.data.LocalColour
import com.bcit.myminiappoliviagrace.data.Repository


/**
 * Olivia Grace, A01363245
 */


/**
 * The ColourState class holds state information related to colours and methods for adding colours
 * to the database and retrieving colours from the CSS Colors REST API.
 */
class ColourState(
    private val colourRepository: ColourRepository,
    private val repository: Repository
) {

    /**
     * An nullable instance of a Colour object.
     */
    var colour by mutableStateOf<Colour?>(null)

    /**
     * A list of Colour objects.
     */
    var colourList = mutableListOf<Colour>().toMutableStateList()

    /**
     * The name of a colour.
     */
    var colourName by mutableStateOf("")

    /**
     * Sets the value of colourName.
     */
    val onColourNameChanged:(String)->Unit = {
        colourName = it
    }

    /**
     * Adds the colours in colourList into the database with the specified project ID.
     */
    fun add(pid: Int) {
        colourList.forEach{
            repository.addColour(
                LocalColour(name = it.colourInfo?.name, rgb = it.colourInfo?.rgb, projectID = pid)
            )
        }
    }

    /**
     * Gets a colour from the CSS Colors REST APU using the specified name.
     */
    suspend fun getColour(colourName: String) : Colour{
        return colourRepository.getColour(colourName)
    }
}