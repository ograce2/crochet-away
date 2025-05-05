package com.bcit.myminiappoliviagrace

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bcit.myminiappoliviagrace.state.ProjectState
import com.bcit.myminiappoliviagrace.state.UserState


/**
 * Olivia Grace, A01363245
 */


/**
 * The Home composable function creates the UI for the Home page.
 */
@Composable
fun Home(navController: NavController, userState : UserState, projectState: ProjectState){

    Log.i("home", "userID = ${userState.loggedInUser}")


    val stateList = remember{
        projectState.getUserProjects(userState.loggedInUser?:0).toMutableStateList()
    }

    LazyColumn{
        item{
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ){
                Text("Create New Project", fontSize = 20.sp, fontWeight = FontWeight.Light)
                IconButton(onClick = {
                    navController.navigate("create")
                }) {
                    Icon(Icons.Default.Add, contentDescription = null)
                }
            }
        }

        items(stateList.count()){
            MyCard(stateList[it].pid?:0, stateList[it].projectName?:"no name",
                stateList[it].endDate != null, navController)
        }

    }
}


/**
 * The MyCard function creates the UI for the project cards displayed on the Home page of the app.
 */
@Composable
fun MyCard(projectID: Int, projectName: String, finished: Boolean, navController: NavController) {
    Card(
        elevation = CardDefaults.cardElevation(1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 12.dp)
            .clickable {
                navController.navigate("info/${projectID}")
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .size(110.dp)
                .background(color = Color(0xFFF9B9B7))
            ) {
            Text(projectName, fontSize = 30.sp, fontWeight = FontWeight.Light, modifier = Modifier.padding(12.dp))

            if (finished){
                Icon(
                    Icons.Outlined.Check,
                    contentDescription = null,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
}