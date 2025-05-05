package com.bcit.myminiappoliviagrace.state

import com.bcit.myminiappoliviagrace.data.LocalUser
import com.bcit.myminiappoliviagrace.data.Repository


/**
 * Olivia Grace, A01363245
 */


/**
 * The UserState class contains the state information related to a user.
 */
class UserState(private val repository: Repository) {

    /**
     * The currently logged in user.
     */
    var loggedInUser : Int? = null

    /**
     * Sets the logged int user ID.
     */
    fun setLoggedInIUser(userID : Int?){
        loggedInUser = userID
    }

    /**
     * Adds a new user to the database.
     */
    fun add(localUser: LocalUser){
        repository.insertUser(localUser)
    }

    /**
     * Gets the user ID for a specific user name from the database.
     */
    fun getUserID(userName: String) : Int?{
        return repository.getUserID(userName)
    }

    /**
     * Gets the password related to a specific username from the database.
     */
    fun getPassword(userName: String) : String?{
        return repository.getUserPassword(userName)
    }

}