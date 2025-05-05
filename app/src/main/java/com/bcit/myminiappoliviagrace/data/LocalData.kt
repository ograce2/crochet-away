package com.bcit.myminiappoliviagrace.data

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase


/**
 * Olivia Grace, A01363245
 */


/**
 * The user table contains information about a user including their username and password.
 */
@Entity(tableName = "user_table")
data class LocalUser(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "user_name") val userName: String?,
    val password: String?
)


/**
 * The project table contains information about a project including the user the project belongs
 * to and the project's name, start date, end date, pattern, notes, and number of rows completed.
 */
@Entity(tableName = "project_table")
data class LocalProject(
    @PrimaryKey(autoGenerate = true) val pid: Int? = null,
    @ColumnInfo(name = "user_id") val userID: Int?,
    @ColumnInfo(name = "project_name") val projectName: String?,
    @ColumnInfo(name = "start_date") val startDate: String?,
    @ColumnInfo(name = "end_date") val endDate: String?,
    @ColumnInfo(name = "pattern_link") val patternLink: String?,
    val notes: String?,
    @ColumnInfo(name = "num_rows") val numRows: Int?
)


/**
 * The project colours table contains information about colours belonging to a specific project.
 * Each row contains the colours' name, rgb value, and the id of the project it belongs to.
 */
@Entity(tableName = "project_colours")
data class LocalColour(
    @PrimaryKey(autoGenerate = true) val cid: Int? = null,
    val name: String?,
    val rgb: String?,
    @ColumnInfo(name = "project_id") val projectID: Int?
)


/**
 * The UserDao contains querires and an insert statement for retrieving information and adding
 * new users to the user table.
 */
@Dao
interface UserDao {

    /**
     * Gets a specific user from the user table using a user ID.
     */
    @Query("SELECT * FROM user_table WHERE uid = :userID")
    fun getUser(userID: Int) : LocalUser?

    /**
     * Gets all of the user from the user table.
     */
    @Query("SELECT  * FROM user_table")
    fun getAll() : List<LocalUser>

    /**
     * Gets the user ID for a user in the user table using their username.
     */
    @Query("SELECT uid FROM user_table WHERE user_name = :userName")
    fun getUserID(userName: String) : Int?

    /**
     * Gets the password for a user in the user table using their password.
     */
    @Query("SELECT password FROM user_table WHERE user_name = :userName")
    fun getPassword(userName: String) : String?

    /**
     * Inserts a new user into the user table.
     */
    @Insert
    fun add(user: LocalUser)
}

/**
 * The ProjectDao contains queries and an insert statement for retrieving, updating, and adding new
 * projects to the projects table.
 */
@Dao
interface ProjectDao{

    /**
     * Inserts a new project into the project table
     */
    @Insert
    fun add(project: LocalProject)

    /**
     * Selects all the projects from the project table for a specific user ID.
     */
    @Query("SELECT * FROM project_table WHERE user_id = :userID")
    fun getUserProjects(userID: Int) : List<LocalProject>

    /**
     * Selects a project from the project table with a specified project ID.
     */
    @Query("SELECT * FROM project_table WHERE pid = :projectID")
    fun getProject(projectID: Int) : LocalProject

    /**
     * Sets the number of rows for a specific project using the project ID.
     */
    @Query("UPDATE project_table SET num_rows = :numRows WHERE pid = :projectID")
    fun setNumRows(numRows : Int, projectID : Int)

    /**
     * Sets the end date for a specific project using the project ID.
     */
    @Query("UPDATE project_table SET end_date = :endDate WHERE pid = :projectID")
    fun setEndDate(endDate : String, projectID : Int)

    /**
     * Gets the most recently added project from the project table.
     */
    @Query("SELECT MAX(pid) FROM project_table")
    fun getTopID() : Int

}


/**
 * The ColourDao contains an insert statement and query for inserting a new colour and retrieving
 * colours for a specified project.
 */
@Dao
interface ColourDao{

    /**
     * Adds a new colour to the project colours table.
     */
    @Insert
    fun add(colour: LocalColour)

    /**
     * Gets all the projects from the project colours table that have a specific project ID.
     */
    @Query("SELECT * FROM project_colours WHERE project_id = :pid")
    fun getColoursByProject(pid: Int): List<LocalColour>

}


/**
 * The database that holds the user table, project table, and project colours table.
 */
@Database(entities = [LocalUser::class, LocalProject::class, LocalColour::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao() : UserDao
    abstract fun projectDao() : ProjectDao
    abstract fun colourDao() : ColourDao
}


/**
 * A singleton instance of the database.
 */
object MyDatabase{
    fun getDatabase(context: Context) : AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "my_db")
            .allowMainThreadQueries()
            .build()
    }
}