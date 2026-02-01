package com.example.itew4_casestudy.presentation.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.itew4_casestudy.R
import com.example.itew4_casestudy.navigation.Routes

@Composable
fun RegistrationScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    var userName by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircleLayout(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .size(100.dp)
                    .shadow(
                        elevation = 10.dp,
                        shape = CircleShape,
                        clip = false
                    )
                    .border(
                        width = 5.dp,
                        color = Color(red = 13, green = 61, blue = 3),
                        shape = CircleShape
                    )
                    .clip(CircleShape),
                innerComposable = {
                    Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = painterResource(R.drawable.pnclogo),
                        contentDescription = "PNC Logo"
                    )
                }
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(red = 179, green = 204, blue = 175)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmboldenedTextTemplate(
                    text = "Welcome to PNC Buddy",
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 5.dp)
                )

                NormalTextTemplate(
                    text = "Sign up now",
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 20.dp)
                )
            }
        }

        TextFieldLayout(
            layoutLabel = "Input Username",
            innerComposable = {
                TextFieldTemplate(
                    value = userName,
                    valueUpdate = {
                        userName = it
                    },
                    textFieldLabel = "Username",
                    icon = Icons.Filled.Person
                )
            }
        )

        TextFieldLayout(
            layoutLabel = "Input Full Name",
            innerComposable = {
                TextFieldTemplate(
                    value = name,
                    valueUpdate = {
                        name = it
                    },
                    textFieldLabel = "Full Name",
                    icon = Icons.Filled.Person
                )
            }
        )

        TextFieldLayout(
            layoutLabel = "Input Password",
            innerComposable = {
                PasswordTextFieldTemplate(
                    value = password,
                    valueUpdate = {
                        password = it
                    },
                    textFieldLabel = "Password",
                    leadingIcon = Icons.Filled.Lock
                )
            }
        )

        TextFieldLayout(
            layoutLabel = "Confirm Password",
            innerComposable = {
                PasswordTextFieldTemplate(
                    value = confirmPassword,
                    valueUpdate = {
                        confirmPassword = it
                    },
                    textFieldLabel = "Confirm Password",
                    leadingIcon = Icons.Filled.Lock
                )
            }
        )

        ButtonTemplate(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
            onClick = { },
            buttonText = "Register"
        )

        LogInOrSignUpLayout(
            initialText = "Already have an account? ",
            underlinedText = "Log in here",
            onClick = {
                navController.navigate(Routes.LOGIN_SCREEN)
            }
        )
    }
}