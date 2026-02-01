package com.example.itew4_casestudy.presentation.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
fun AnnouncementsScreen(navController: NavController) {
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
            },
            bottomBar = { BottomBarLayout( navController = navController, selectionIdentifier = 1) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
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
                            imageVector = Icons.Filled.Announcement,
                            contentDescription = "Announcement Icon",
                            tint = Color(red = 13, green = 61, blue = 3)
                        )

                        EmboldenedTextTemplate(
                            modifier = Modifier
                                .padding(top = 20.dp, bottom = 10.dp),
                            text = "University Announcements",
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
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    items(3) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 10.dp, end = 10.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.End
                        ) {
                            ButtonTemplate(
                                onClick = {  },
                                buttonText = "Mark as read",
                                fontSize = 13.sp
                            )
                        }

                        CardTemplate(
                            modifier = Modifier
                                .padding(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(red = 179, green = 204, blue = 175)
                            ),
                            innerComposable = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .padding(
                                                start = 20.dp,
                                                end = 10.dp,
                                                top = 10.dp,
                                                bottom = 10.dp
                                            )
                                            .size(40.dp),
                                        imageVector = Icons.Filled.Campaign,
                                        contentDescription = "Announcement Icon",
                                        tint = Color(red = 13, green = 61, blue = 3)
                                    )

                                    EmboldenedTextTemplate(
                                        modifier = Modifier
                                            .padding(top = 10.dp, bottom = 10.dp),
                                        text = "Announcement"
                                    )
                                }

                                HorizontalDivider(
                                    modifier = Modifier
                                        .padding(start = 20.dp, end = 20.dp, bottom = 5.dp),
                                    thickness = 3.dp,
                                    color = Color(red = 13, green = 61, blue = 3)
                                )

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .padding(start = 20.dp, end = 5.dp, bottom = 5.dp)
                                            .size(15.dp),
                                        imageVector = Icons.Filled.CalendarToday,
                                        contentDescription = "Calendar Icon",
                                        tint = Color(red = 13, green = 61, blue = 3)
                                    )

                                    NormalTextTemplate(
                                        modifier = Modifier
                                            .padding(bottom = 5.dp),
                                        text = "January 31, 2026",
                                        fontSize = 13.sp,
                                        color = Color.Black
                                    )
                                }

                                NormalTextTemplate(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris.",
                                    fontSize = 20.sp,
                                    color = Color.Black
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}