package com.example.itew4_casestudy.presentation.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.itew4_casestudy.navigation.Routes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnnouncementsScreen(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val context = navController.context
    val database = remember {
        com.example.itew4_casestudy.data.local.AppDatabase.getDatabase(context)
    }
    val repository = remember {
        com.example.itew4_casestudy.data.repository.AnnouncementRepository(
            database.announcementDao()
        )
    }
    val factory = remember {
        com.example.itew4_casestudy.presentation.viewmodel.AnnouncementViewModelFactory(repository)
    }
    val viewModel: com.example.itew4_casestudy.presentation.viewmodel.AnnouncementViewModel =
        androidx.lifecycle.viewmodel.compose.viewModel(factory = factory)

    LaunchedEffect(Unit) {
        viewModel.baseAnnouncements()
    }

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
                                .padding(start = 20.dp, end = 30.dp, top = 15.dp, bottom = 5.dp)
                                .size(40.dp),
                            imageVector = Icons.Filled.Announcement,
                            contentDescription = "Announcement Icon",
                            tint = Color(red = 13, green = 61, blue = 3)
                        )

                        EmboldenedTextTemplate(
                            modifier = Modifier
                                .padding(top = 15.dp, bottom = 5.dp),
                            text = "University Announcements"
                        )
                    }

                    HorizontalDivider(
                        modifier = Modifier
                            .padding(10.dp),
                        thickness = 3.dp,
                        color = Color(red = 13, green = 61, blue = 3)
                    )
                }

                var expanded by remember { mutableStateOf(false) }

                val filterOptions = listOf("All", "Read", "Unread")

                val selectedFilter = when (viewModel.currentFilter) {
                    com.example.itew4_casestudy.presentation.viewmodel.AnnouncementFilter.ALL -> "All"
                    com.example.itew4_casestudy.presentation.viewmodel.AnnouncementFilter.READ -> "Read"
                    com.example.itew4_casestudy.presentation.viewmodel.AnnouncementFilter.UNREAD -> "Unread"
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = selectedFilter,
                            onValueChange = {},
                            readOnly = true,
                            placeholder = { Text("Filter Announcements") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            },
                            textStyle = TextStyle(fontSize = 15.sp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(red = 13, green = 61, blue = 3),
                                unfocusedBorderColor = Color(red = 13, green = 61, blue = 3)
                            ),
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            filterOptions.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        expanded = false
                                        when (option) {
                                            "All" -> viewModel.setFilter(
                                                com.example.itew4_casestudy.presentation.viewmodel.AnnouncementFilter.ALL
                                            )
                                            "Read" -> viewModel.setFilter(
                                                com.example.itew4_casestudy.presentation.viewmodel.AnnouncementFilter.READ
                                            )
                                            "Unread" -> viewModel.setFilter(
                                                com.example.itew4_casestudy.presentation.viewmodel.AnnouncementFilter.UNREAD
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(viewModel.announcements) {announcement ->
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
                                                end = 30.dp,
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
                                        text = announcement.title
                                    )
                                }

                                HorizontalDivider(
                                    modifier = Modifier
                                        .padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
                                    thickness = 3.dp,
                                    color = Color(red = 13, green = 61, blue = 3)
                                )

                                if (!announcement.isRead) {
                                    NormalTextTemplate(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
                                        text = announcement.content,
                                        textAlign = TextAlign.Left,
                                        fontSize = 15.sp
                                    )

                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 10.dp, end = 10.dp,  bottom = 20.dp),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        ButtonTemplate(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            onClick = {
                                                viewModel.markAsRead(announcement.id)
                                            },
                                            buttonText = "Mark as read",
                                            fontSize = 18.sp
                                        )
                                    }
                                } else {
                                    NormalTextTemplate(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                                        text = announcement.content,
                                        textAlign = TextAlign.Left,
                                        fontSize = 15.sp
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}