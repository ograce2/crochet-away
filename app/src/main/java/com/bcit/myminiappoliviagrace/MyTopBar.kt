package com.bcit.myminiappoliviagrace

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ExitToApp
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


/**
 * Olivia Grace, A01363245
 */


/**
 * The MyTopBar composabe function creates the navigation bar for the app.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(navController: NavController, navTitle: String, enableButtons: Boolean){
    CenterAlignedTopAppBar(
        colors = TopAppBarColors(
            Color(0xFF69995D),
            Color(0xFFF8E9E9),
            Color(0xFFF8E9E9),
            Color(0xFFF8E9E9),
            Color(0xFFF8E9E9)),
        navigationIcon = {
            IconButton(
                enabled = enableButtons,
                onClick = {
                    navController.navigate("home")
                }
            ){
                Icon(Icons.Rounded.Home, contentDescription = null, modifier = Modifier.size(30.dp))
            }
        },
        title = {
            Text(navTitle, fontSize = 30.sp, fontWeight = FontWeight.Light)
        } ,
        actions = {
            IconButton(
                enabled = enableButtons,
                onClick = {
                    navController.navigate("login")
                }
            ){
                Icon(Icons.AutoMirrored.Rounded.ExitToApp, contentDescription = null,
                    modifier = Modifier.size(30.dp))
            }

        }
    )
}