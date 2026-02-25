package com.example.itew4_casestudy.presentation.screens

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.itew4_casestudy.navigation.Routes
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen(navController: NavController) {
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
    ) {
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
            }
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
                        .padding(top = 20.dp, bottom = 5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircleLayout(
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .size(90.dp)
                            .shadow(
                                elevation = 10.dp,
                                shape = CircleShape,
                                clip = false
                            )
                            .border(
                                width = 3.dp,
                                color = Color(red = 13, green = 61, blue = 3),
                                shape = CircleShape
                            )
                            .clip(CircleShape),
                        innerComposable = {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color(red = 179, green = 204, blue = 175)),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                EmboldenedTextTemplate(
                                    text = "J",
                                    fontSize = 30.sp
                                )
                            }
                        }
                    )

                    NormalTextTemplate(
                        modifier = Modifier
                            .padding(bottom = 5.dp),
                        text = "Dangal Greetings!",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    )

                    EmboldenedTextTemplate(
                        modifier = Modifier
                            .padding(top = 5.dp),
                        text = "John Doe",
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp
                    )
                }


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, bottom = 10.dp)
                        .background(Color(red = 179, green = 204, blue = 175)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    EmboldenedTextTemplate(
                        text = "Welcome to PNC Buddy",
                        modifier = Modifier
                            .padding(top = 15.dp, bottom = 5.dp)
                    )

                    NormalTextTemplate(
                        text = "What shall we do today?",
                        modifier = Modifier
                            .padding(top = 5.dp, bottom = 15.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .fillMaxWidth()
                        .height(150.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CardTemplate(
                        modifier = Modifier
                            .weight(.5f)
                            .fillMaxHeight()
                            .padding(start = 10.dp, end = 10.dp),
                        onClick = {
                            navController.navigate(Routes.ANNOUNCEMENT_SCREEN)
                        },
                        innerComposable = {
                            OptionLayout(
                                modifier = Modifier
                                    .fillMaxSize(.5f)
                                    .padding(bottom = 10.dp),
                                imageVector = Icons.Filled.Announcement,
                                optionText = "Announcements"
                            )
                        }
                    )

                    CardTemplate(
                        modifier = Modifier
                            .weight(.4f)
                            .fillMaxHeight()
                            .padding(start = 10.dp, end = 10.dp),
                        onClick = {
                            navController.navigate(Routes.INFORMATION_SCREEN)
                        },
                        innerComposable = {
                            OptionLayout(
                                modifier = Modifier
                                    .fillMaxSize(.5f)
                                    .padding(bottom = 10.dp),
                                imageVector = Icons.Filled.School,
                                optionText = "Campus Info"
                            )
                        }
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .fillMaxWidth()
                        .height(150.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CardTemplate(
                        modifier = Modifier
                            .weight(.4f)
                            .fillMaxHeight()
                            .padding(start = 10.dp, end = 10.dp),
                        onClick = {
                            navController.navigate(Routes.SETTINGS_SCREEN)
                        },
                        innerComposable = {
                            OptionLayout(
                                modifier = Modifier
                                    .fillMaxSize(.5f)
                                    .padding(bottom = 10.dp),
                                imageVector = Icons.Filled.Settings,
                                optionText = "Settings"
                            )
                        }
                    )

                    CardTemplate(
                        modifier = Modifier
                            .weight(.5f)
                            .fillMaxHeight()
                            .padding(start = 10.dp, end = 10.dp),
                        onClick = {
                            navController.navigate(Routes.TASK_SCREEN)
                        },
                        innerComposable = {
                            OptionLayout(
                                modifier = Modifier
                                    .fillMaxSize(.5f)
                                    .padding(bottom = 10.dp),
                                imageVector = Icons.Filled.Task,
                                optionText = "Tasks"
                            )
                        }
                    )
                }
            }
        }
    }
}
