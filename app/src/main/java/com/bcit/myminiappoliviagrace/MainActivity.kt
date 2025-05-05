package com.bcit.myminiappoliviagrace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bcit.myminiappoliviagrace.data.ColourRepository
import com.bcit.myminiappoliviagrace.data.MyDatabase
import com.bcit.myminiappoliviagrace.data.Repository
import com.bcit.myminiappoliviagrace.data.client
import com.bcit.myminiappoliviagrace.state.ProjectState
import com.bcit.myminiappoliviagrace.state.UserState


/**
 * Olivia Grace, A01363245
 */


/**
 * The MainActivity class contains the methods and attributes used for navigating the app,
 * accessing the CSS Colors REST API, and usaing the database.
 */
class MainActivity : ComponentActivity() {

    /**
     * The repository used for making HTTP requests to the CSS Colors REST API.
     */
    private val colourRepository by lazy{
        ColourRepository(client)
    }

    /**
     * The database for the app.
     */
    private val db by lazy{
        MyDatabase.getDatabase(applicationContext)
    }

    /**
     * The repository used for accessing the database.
     */
    private val repo by lazy{
        Repository(db.userDao(), db.projectDao(), db.colourDao())
    }

    /**
     * Overrides the onCreate method. Creates the UserState and ProjectState and calls MainContent.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val userState = remember{
                UserState(repo)
            }

            val projectState = remember{
                ProjectState(repo)
            }

            MainContent(userState, projectState)
        }
    }

    /**
     * The MainContent composable function manages the navigation of the app.
     */
    @Composable
    fun MainContent(userState: UserState, projectState: ProjectState){

        /**
         * Controls navigation of the app.
         */
        val navController = rememberNavController()

        /**
         * Determines if the navigation buttons should be enabled.
         */
        var enableNavButtons : Boolean by remember{
            mutableStateOf(false)
        }

        Scaffold(
            topBar = {
                MyTopBar(navController, "Crochet Away", enableNavButtons)
            }
        ){ padding ->
            NavHost(navController,
                "login",
                modifier = Modifier.padding(padding)
            ) {

                composable("home"){
                    enableNavButtons = true
                    Home(navController, userState, projectState)
                }

                composable("info/{projectID}"){
                    enableNavButtons = true
                    val projectID = it.arguments?.getString("projectID")?.toInt()
                    Info(projectID, projectState)
                }

                composable("login"){
                    enableNavButtons = false
                    Login(navController, userState)
                }

                composable("create"){
                    CreateProject(navController, userState, projectState, colourRepository, repo)
                }
            }
        }
    }
}


