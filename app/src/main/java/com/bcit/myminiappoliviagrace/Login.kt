package com.bcit.myminiappoliviagrace

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bcit.myminiappoliviagrace.data.LocalUser
import com.bcit.myminiappoliviagrace.state.LoginState
import com.bcit.myminiappoliviagrace.state.UserState


/**
 * Olivia Grace, A01363245
 */


/**
 * The Login composable function creates the UI for the login page of the app.
 */
@Composable
fun Login(navController: NavController, userState: UserState){

    val loginState = remember{ LoginState() }


    Box(
        modifier = Modifier.safeDrawingPadding()
    ) {
        Column (verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
            ){
            Text("Login or Sign Up", fontSize = 30.sp, fontWeight = FontWeight.Light)
            Spacer(modifier = Modifier.size(30.dp))
            Text("Username:", fontSize = 20.sp, fontWeight = FontWeight.Light)
            LoginTextField(
                loginState.name,
                loginState.onNameChanged,

            )
            Spacer(modifier = Modifier.size(30.dp))
            Text("Password:", fontSize = 20.sp, fontWeight = FontWeight.Light)
            LoginTextField(
                loginState.password,
                loginState.onPasswordChanged
            )
            Spacer(modifier = Modifier.size(30.dp))
            Row {
                Button(onClick = {
                    // check if user in db
                    val password = userState.getPassword(loginState.name)
                    if (password == null) {
                        userState.add(
                            LocalUser(
                                userName = loginState.name,
                                password = loginState.password
                            )
                        )
                        val userID = userState.getUserID(loginState.name)
                        if (userID != null) {
                            userState.setLoggedInIUser(userID)
                            Log.i("signup debug", "user id $userID")
                            navController.navigate("home")
                        }

                    } else {
                        loginState.name = ""
                        loginState.password = ""
                        Log.i("signup debug", "user already in database, login instead")
                    }


                },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF394648))
                ) {
                    Text("Sign Up", fontSize = 20.sp, fontWeight = FontWeight.Light)
                }
                Spacer(modifier = Modifier.size(30.dp))
                Button(
                    onClick = {
                    val password = userState.getPassword(loginState.name)
                    if (password != null && password == loginState.password){
                        Log.i("login debug","password $password")
                        val userID = userState.getUserID(loginState.name)
                        if (userID != null){
                            userState.setLoggedInIUser(userID)
                            Log.i("login debug","userID $userID")
                            navController.navigate("home")
                        }

                    } else{
                        loginState.name = ""
                        loginState.password = ""
                        Log.i("login debug","pwd: $password")
                        Log.i("login debug","user not in database, sign up instead")
                    }
                },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF394648))
                ) {
                    Text("Login", fontSize = 20.sp, fontWeight = FontWeight.Light)
                }
            }

        }
    }
}


/**
 * The LoginTextField composable function creates for the text fields used for logging in.
 */
@Composable
fun LoginTextField(value:String, onValueChanged:(String)->Unit, invalid:Boolean = false){
    TextField(
        value = value, onValueChange= onValueChanged,
        textStyle = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Light),
        isError = invalid,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF8E9E9),
            unfocusedContainerColor = Color(0xFFF8E9E9),
            disabledContainerColor = Color(0xFFF8E9E9),
            errorContainerColor = Color(0xFFF8E9E9))
    )
}