package com.bcit.myminiappoliviagrace

import android.util.Log
import androidx.collection.MutableIntList
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bcit.myminiappoliviagrace.data.Colour
import com.bcit.myminiappoliviagrace.data.ColourRepository
import com.bcit.myminiappoliviagrace.data.LocalProject
import com.bcit.myminiappoliviagrace.data.Repository
import com.bcit.myminiappoliviagrace.state.ColourState
import com.bcit.myminiappoliviagrace.state.CreateProjectState
import com.bcit.myminiappoliviagrace.state.ProjectState
import com.bcit.myminiappoliviagrace.state.UserState
import kotlinx.coroutines.launch
import java.time.LocalDate


/**
 * Olivia Grace, A01363245
 */


/**
 * The CreateProject composable function contains the functionality for creating the UI for the
 * Create Project page.
 */
@Composable
fun CreateProject(navController: NavController, userState : UserState, projectState: ProjectState,
                  colourRepository: ColourRepository, repository: Repository
){

    val colourState = remember{ ColourState(colourRepository, repository) }

    val createProjectState = remember{ CreateProjectState() }


    Box(modifier = Modifier.safeDrawingPadding()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 12.dp)
        ) {
            Text("Name:", fontSize = 20.sp, fontWeight = FontWeight.Light)
            ProjectTextField(
                createProjectState.name,
                createProjectState.onNameChanged
            )
            Spacer(modifier = Modifier.size(20.dp))
            Text("Pattern Link:", fontSize = 20.sp, fontWeight = FontWeight.Light)
            ProjectTextField(
                createProjectState.patternLink,
                createProjectState.onPatternLinkChanged
            )
            Spacer(modifier = Modifier.size(20.dp))
            Text("Notes:", fontSize = 20.sp, fontWeight = FontWeight.Light)
            ProjectTextField(
                createProjectState.notes,
                createProjectState.onNotesChanged
            )
            Spacer(modifier = Modifier.size(20.dp))
            Text("Enter a Colour:", fontSize = 20.sp, fontWeight = FontWeight.Light)

            ColourSearch(colourState)
            Spacer(modifier = Modifier.size(20.dp))
            LazyRow{
                items(colourState.colourList.size){
                    ColourCard(colourState.colourList[it])
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            Button(onClick = {
                projectState.add(
                    LocalProject(
                        userID = userState.loggedInUser,
                        projectName = createProjectState.name,
                        startDate = LocalDate.now().toString(),
                        endDate = null,
                        patternLink = createProjectState.patternLink,
                        notes = createProjectState.notes,
                        numRows = 0
                    )
                )
                val pid = projectState.getTopID()
                Log.i("TEST", "$pid")
                colourState.add(pid)
                navController.navigate("home")
            },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF394648))
            ) {

                Text("Create Project", fontSize = 20.sp, fontWeight = FontWeight.Light)
            }
        }
    }

}

/**
 * The ColourSearch composable function contains a textbox and button where the user can type
 * in colour names and an HTTP request is made to the CSS Colors REST API. If the colour name
 * exists, then that colour is displayed for the user.
 */
@Composable
fun ColourSearch(colourState: ColourState) {
    val scope = rememberCoroutineScope()
    ProjectTextField(
        colourState.colourName,
        colourState.onColourNameChanged
    )
    Spacer(modifier = Modifier.size(5.dp))
        Button(onClick = {
            scope.launch{
                val newColour = colourState.getColour(colourState.colourName.filterNot{
                    it.isWhitespace()
                })
                colourState.colour = newColour
                if (colourState.colour != null && newColour.status == 200){
                    colourState.colourList.add(newColour)
                }
                colourState.colourName = ""
            }
        },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF394648))
        ){
            Text("Get Colour", fontSize = 20.sp, fontWeight = FontWeight.Light)
        }
}

/**
 * The ProjectTextField composable function creates the UI for the TextField components on the
 * Create Project page.
 */
@Composable
fun ProjectTextField(value:String, onValueChanged:(String)->Unit){
    TextField(
        value = value, onValueChange= onValueChanged,
        textStyle = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Light),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF8E9E9),
            unfocusedContainerColor = Color(0xFFF8E9E9),
            disabledContainerColor = Color(0xFFF8E9E9),
            errorContainerColor = Color(0xFFF8E9E9))
    )
}

/**
 * The ColourCard composable function is used for displaying a Colour object for the user using
 * the rgb string of the colour object.
 */
@Composable
fun ColourCard(colour: Colour){

    val rgbList = colour.colourInfo?.rgb?.split(",") ?: listOf("0","0","0")
    val rgbListInt = MutableIntList()
    rgbList.forEach{
        rgbListInt.add(it.toInt())
    }

    Card(
        elevation = CardDefaults.cardElevation(1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 12.dp)){
    }
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(color = Color(rgbListInt[0], rgbListInt[1], rgbListInt[2]))){
    }
}

