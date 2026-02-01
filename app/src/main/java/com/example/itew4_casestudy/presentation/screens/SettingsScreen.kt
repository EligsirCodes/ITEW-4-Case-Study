package com.example.itew4_casestudy.presentation.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
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
fun SettingsScreen(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var isDarkMode by remember { mutableStateOf(false) }
    var isNotificationsOn by remember { mutableStateOf(false) }

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
            bottomBar = { BottomBarLayout( navController = navController, selectionIdentifier = 5) }
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
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Settings Icon",
                            tint = Color(red = 13, green = 61, blue = 3)
                        )

                        EmboldenedTextTemplate(
                            modifier = Modifier
                                .padding(top = 20.dp, bottom = 10.dp),
                            text = "Settings",
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
                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(red = 179, green = 204, blue = 175)
                        ),
                        innerComposable = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                EmboldenedTextTemplate(
                                    modifier = Modifier
                                        .padding(20.dp),
                                    text = "Dark Mode"
                                )

                                Switch(
                                    modifier = Modifier
                                        .padding(end = 20.dp),
                                    checked = isDarkMode,
                                    onCheckedChange = { isDarkMode = it },
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = Color.White,
                                        checkedTrackColor = Color(red = 13, green = 61, blue = 3),
                                        uncheckedThumbColor = Color(red = 13, green = 61, blue = 3),
                                        uncheckedTrackColor = Color.White,
                                        checkedBorderColor = Color.White,
                                        uncheckedBorderColor = Color(red = 13, green = 61, blue = 3)
                                    )
                                )
                            }
                        }
                    )
                    CardTemplate(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(red = 179, green = 204, blue = 175)
                        ),
                        innerComposable = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                EmboldenedTextTemplate(
                                    modifier = Modifier
                                        .padding(20.dp),
                                    text = "Notifications"
                                )

                                Switch(
                                    modifier = Modifier
                                        .padding(end = 20.dp),
                                    checked = isNotificationsOn,
                                    onCheckedChange = { isNotificationsOn = it },
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = Color.White,
                                        checkedTrackColor = Color(red = 13, green = 61, blue = 3),
                                        uncheckedThumbColor = Color(red = 13, green = 61, blue = 3),
                                        uncheckedTrackColor = Color.White,
                                        checkedBorderColor = Color.White,
                                        uncheckedBorderColor = Color(red = 13, green = 61, blue = 3)
                                    )
                                )
                            }
                        }
                    )
                }
            }
        }
    }

}