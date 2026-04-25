package com.example.itew4_casestudy.presentation.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.itew4_casestudy.navigation.Routes
import com.example.itew4_casestudy.presentation.viewmodel.ThemeViewModel
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(navController: NavController, themeViewModel: ThemeViewModel) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val isDarkMode by themeViewModel.isDarkMode.collectAsState()
    val isNotificationsOn by themeViewModel.isNotificationsEnabled.collectAsState()

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
                                .padding(start = 20.dp, end = 30.dp, top = 15.dp, bottom = 5.dp)
                                .size(40.dp),
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Settings Icon",
                            tint = MaterialTheme.colorScheme.primary
                        )

                        EmboldenedTextTemplate(
                            modifier = Modifier
                                .padding(top = 15.dp, bottom = 5.dp),
                            text = "Settings"
                        )
                    }

                    HorizontalDivider(
                        modifier = Modifier
                            .padding(10.dp),
                        thickness = 3.dp,
                        color = MaterialTheme.colorScheme.primary
                    )

                    CardTemplate(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
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
                                        .padding(15.dp),
                                    text = "Dark Mode",
                                    fontSize = 18.sp
                                )

                                Switch(
                                    modifier = Modifier
                                        .padding(end = 20.dp),
                                    checked = isDarkMode,
                                    onCheckedChange = { themeViewModel.toggleDarkMode(it) },
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = Color.White,
                                        checkedTrackColor = MaterialTheme.colorScheme.primary,
                                        uncheckedThumbColor = MaterialTheme.colorScheme.primary,
                                        uncheckedTrackColor = MaterialTheme.colorScheme.background,
                                        checkedBorderColor = Color.White,
                                        uncheckedBorderColor = MaterialTheme.colorScheme.primary
                                    )
                                )
                            }
                        }
                    )

                    CardTemplate(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
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
                                        .padding(15.dp),
                                    text = "Notifications",
                                    fontSize = 18.sp
                                )

                                Switch(
                                    modifier = Modifier
                                        .padding(end = 20.dp),
                                    checked = isNotificationsOn,
                                    onCheckedChange = { themeViewModel.toggleNotifications(it) },
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = Color.White,
                                        checkedTrackColor = MaterialTheme.colorScheme.primary,
                                        uncheckedThumbColor = MaterialTheme.colorScheme.primary,
                                        uncheckedTrackColor = MaterialTheme.colorScheme.background,
                                        checkedBorderColor = Color.White,
                                        uncheckedBorderColor = MaterialTheme.colorScheme.primary
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