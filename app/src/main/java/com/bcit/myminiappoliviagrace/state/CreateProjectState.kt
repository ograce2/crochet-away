package com.bcit.myminiappoliviagrace.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


/**
 * Olivia Grace, A01363245
 */


/**
 * The CreateProjectState holds state information related to creating a new project.
 */
class CreateProjectState {

    /**
     * The name of a project.
     */
    var name by mutableStateOf("")

    /**
     * Sets the value of name.
     */
    val onNameChanged:(String)->Unit = {
        name = it
    }

    /**
     * The notes of a project.
     */
    var notes by mutableStateOf("")

    /**
     * Sets the value of notes.
     */
    var onNotesChanged:(String) -> Unit = {
        notes = it
    }

    /**
     * The pattern link of a project.
     */
    var patternLink by mutableStateOf("")

    /**
     * Sets the value of patternLink.
     */
    var onPatternLinkChanged: (String) -> Unit = {
        patternLink = it
    }

}



