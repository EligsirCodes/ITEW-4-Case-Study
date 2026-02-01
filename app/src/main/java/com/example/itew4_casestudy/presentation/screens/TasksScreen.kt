package com.example.itew4_casestudy.presentation.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Task
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.itew4_casestudy.navigation.Routes
import kotlinx.coroutines.launch

@Composable
fun TaskScreen(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            BurgerStackMenuLayout(

                onSettingsClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    navController.navigate(Routes.SETTINGS_SCREEN)
                },

                onLogoutClick = {
                    val context = navController.context
                    val sharedPrefs = context.getSharedPreferences(
                        "user_session",
                        Context.MODE_PRIVATE
                    )

                    sharedPrefs.edit()
                        .putBoolean("is_logged_in", false)
                        .apply()

                    scope.launch {
                        drawerState.close()
                    }

                    navController.navigate(Routes.LOGIN_SCREEN) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    ){
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                TopBarLayout(
                    onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            },
            bottomBar = { BottomBarLayout( navController = navController, selectionIdentifier = 4) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(start = 20.dp, end = 10.dp, top = 20.dp, bottom = 10.dp)
                                .size(50.dp),
                            imageVector = Icons.Filled.Task,
                            contentDescription = "Task Icon",
                            tint = Color(red = 13, green = 61, blue = 3)
                        )

                        EmboldenedTextTemplate(
                            modifier = Modifier
                                .padding(top = 20.dp, bottom = 10.dp),
                            text = "Tasks",
                            textAlign = TextAlign.Center,
                            fontSize = 30.sp
                        )
                    }

                    HorizontalDivider(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
                        thickness = 5.dp,
                        color = Color(red = 13, green = 61, blue = 3)
                    )
                    CardTemplate(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(red = 179, green = 204, blue = 175)
                        ),
                        innerComposable = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                EmboldenedTextTemplate(
                                    modifier = Modifier
                                        .padding(20.dp),
                                    text = "No Tasks Yet",
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    )
                }
            }
        }
    }


                }