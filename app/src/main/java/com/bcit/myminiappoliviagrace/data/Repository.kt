package com.bcit.myminiappoliviagrace.data


/**
 * Olivia Grace, A01363245
 */


/**
 * The Repository class is responsible for the data access logic to the database.
 */
class Repository(
    private val userDao: UserDao,
    private val projectDao: ProjectDao,
    private val colourDao: ColourDao
) {

    /**
     * Gets a user from the user table using their user ID.
     */
    fun getUser(id: Int): LocalUser?{
        return userDao.getUser(id)
    }

    /**
     * Inserts a new user into the user table.
     */
    fun insertUser(user:LocalUser){
        userDao.add(user)
    }

    /**
     * Gets all of the users from the user table.
     */
    fun getAllUsers(): List<LocalUser>{
        return userDao.getAll()
    }

    /**
     * Gets the user ID for a user based on their username.
     */
    fun getUserID(userName: String): Int?{
        return userDao.getUserID(userName)
    }

    /**
     * Gets the password for a user based on their username.
     */
    fun getUserPassword(userName: String): String?{
        return userDao.getPassword(userName)
    }

    /**
     * Inserts a new project into the project table.
     */
    fun insertProject(project: LocalProject){
        projectDao.add(project)
    }

    /**
     * Gets all of the projects for a specific user based on their user ID.
     */
    fun getUserProjects(userID: Int) : List<LocalProject>{
        return projectDao.getUserProjects(userID)
    }

    /**
     * Gets a project from a project table using a project ID.
     */
    fun getProject(projectID: Int) : LocalProject{
        return projectDao.getProject(projectID)
    }

    /**
     * Sets the number of rows for a project in the project table using the project ID.
     */
    fun setNumRows(numRows: Int, projectID: Int){
        return projectDao.setNumRows(numRows, projectID)
    }

    /**
     * Sets the end data for a project in the project table using the project ID.
     */
    fun setEndDate(endDate: String, projectID: Int){
        projectDao.setEndDate(endDate, projectID)
    }

    /**
     * Inserts a new colour into the project colours table.
     */
    fun addColour(colour: LocalColour){
        colourDao.add(colour)
    }

    /**
     * Gets the ID of the most recent project from the project table.
     */
    fun getTopProjectID(): Int{
        return projectDao.getTopID()
    }

    /**
     * Gets all of the colour for a project from the project colours table using the project ID.
     */
    fun getProjectColours(pid: Int): List<LocalColour>{
        return colourDao.getColoursByProject(pid)
    }

}

