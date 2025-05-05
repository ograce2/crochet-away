package com.bcit.myminiappoliviagrace

import androidx.collection.MutableIntList
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bcit.myminiappoliviagrace.data.LocalColour
import com.bcit.myminiappoliviagrace.data.LocalProject
import com.bcit.myminiappoliviagrace.state.ProjectState
import java.time.LocalDate


/**
 * Olivia Grace, A01363245
 */


/**
 * The Info composable function contains the UI for displaying the information for an individual
 * project.
 */
@Composable
fun Info(projectID: Int?, projectState: ProjectState){

    val project : LocalProject = projectState.getProject(projectID ?: 0)

    val colours: List<LocalColour> = projectState.getProjectColours(projectID ?: 0)

    var numRows by remember{
        mutableIntStateOf(project.numRows ?: 0)
    }

    var finished by remember{
        mutableStateOf((project.endDate != null))
    }

    Box(
        modifier = Modifier.safeDrawingPadding()
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
                .verticalScroll(rememberScrollState())
        ){
            Text(
                project.projectName ?: "No Name",
                fontSize = 50.sp,
                fontWeight = FontWeight.Light
            )
            Spacer(modifier = Modifier.size(30.dp))
            Row {
                Text(
                    "Started: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light
                )
                Text(
                    "${project.startDate}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraLight
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            if (finished){
                Row {
                    Text(
                        "Finished: ",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Light
                    )
                    Text(
                        project.endDate ?: LocalDate.now().toString(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraLight
                    )
                }
            } else{
                Button(onClick = {
                    if (projectID != null) {
                        projectState.setEndDate(LocalDate.now().toString(), projectID)
                        finished = true

                    }

                },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF394648))
                ) {
                    Text(
                        "Finished",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }
            Spacer(modifier = Modifier.size(30.dp))
            Text(
                "Link to Pattern:",
                fontSize = 30.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                project.patternLink ?: "no link",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraLight
            )
            Spacer(modifier = Modifier.size(30.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Number of rows: ",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Light
                )
                Text(
                    "$numRows",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraLight
                )
                IconButton(
                    enabled = !finished,
                    onClick = {
                        if (project.numRows != null && projectID != null){
                            projectState.setNumRows(numRows  + 1, projectID)
                        }
                        numRows++
                    }){
                    Icon(Icons.Default.Add, contentDescription = null)
                }
            }
            Spacer(modifier = Modifier.size(30.dp))
            Text(
                "Notes:",
                fontSize = 30.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                project.notes ?: "no notes added",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraLight
            )
            Spacer(modifier = Modifier.size(30.dp))
            Text(
                "Colour Scheme:",
                fontSize = 30.sp,
                fontWeight = FontWeight.Light
            )

            LazyRow(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
                ){
                items(colours.size){
                    ColourCard(colours[it])
                }
            }

        }
    }
}

/**
 * The ColourCard composable is used for displaying a colour using the rgb values of a LocalColour
 * object.
 */
@Composable
fun ColourCard(colour: LocalColour){

    val rgbList = colour.rgb?.split(",") ?: listOf("0","0","0")
    val rgbListInt = MutableIntList()
    rgbList.forEach{
        rgbListInt.add(it.toInt())
    }
    Card(
        elevation = CardDefaults.cardElevation(1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 12.dp))
    {

        Box(
            modifier = Modifier
                .size(50.dp)
                .background(color = Color(rgbListInt[0], rgbListInt[1], rgbListInt[2])))

    }

}