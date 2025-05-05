package com.bcit.myminiappoliviagrace.state

import com.bcit.myminiappoliviagrace.data.LocalColour
import com.bcit.myminiappoliviagrace.data.LocalProject
import com.bcit.myminiappoliviagrace.data.Repository


/**
 * Olivia Grace, A01363245
 */


/**
 * The ProjectState class contains state information related to a project.
 */
class ProjectState(private val repository: Repository) {

    /**
     * Adds a new project to the database.
     */
    fun add(localProject: LocalProject){
        repository.insertProject(localProject)
    }

    /**
     * Retrieves all the projects with a specified user ID from the database.
     */
    fun getUserProjects(userID: Int) : List<LocalProject>{
        return repository.getUserProjects(userID)
    }

    /**
     * Gets a project with a specific project ID from the database.
     */
    fun getProject(projectID: Int) : LocalProject{
        return repository.getProject(projectID)
    }

    /**
     * Sets the number of rows for a specific project.
     */
    fun setNumRows(numRows: Int, projectID: Int){
        repository.setNumRows(numRows, projectID)
    }

    /**
     * Sets the end date for a specific project.
     */
    fun setEndDate(endDate: String, projectID: Int){
        repository.setEndDate(endDate, projectID)
    }

    /**
     * Gets the most recently added project from the database.
     */
    fun getTopID() : Int{
        return repository.getTopProjectID()
    }

    /**
     * Gets the colours related to a specific project.
     */
    fun getProjectColours(pid: Int): List<LocalColour>{
        return repository.getProjectColours(pid)
    }
}