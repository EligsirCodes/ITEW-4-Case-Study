package com.example.itew4_casestudy.presentation.screens

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.itew4_casestudy.R
import com.example.itew4_casestudy.navigation.Routes

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val sharedPrefs = context.getSharedPreferences(
        "user_session",
        Context.MODE_PRIVATE
    )

    val scrollState = rememberScrollState()
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

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
                    .size(220.dp)
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
                    text = "Log in to your account",
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 20.dp)
                )
            }
        }
    }
}