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