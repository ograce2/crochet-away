package com.bcit.myminiappoliviagrace.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


/**
 * Olivia Grace, A01363245
 */


/**
 * The LoginState class contains the state information used for logging in or signing up a user.
 */
class LoginState{

    /**
     * The name used to login.
     */
    var name by mutableStateOf("")

    /**
     * Sets the value of name.
     */
    val onNameChanged:(String)->Unit = {
        name = it
    }

    /**
     * The password of a project.
     */
    var password by mutableStateOf("")

    /**
     * Sets the password of a project.
     */
    val onPasswordChanged:(String)->Unit = {
        password = it
    }

}