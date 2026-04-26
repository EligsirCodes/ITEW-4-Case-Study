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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.itew4_casestudy.navigation.Routes
import com.example.itew4_casestudy.presentation.viewmodel.AnnouncementViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnnouncementsScreen(navController: NavController, viewModel: AnnouncementViewModel) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var showAddDialog by remember { mutableStateOf(false) }
    var newAnnouncementTitle by remember { mutableStateOf("") }
    var newAnnouncementContent by remember { mutableStateOf("") }

    var userRole by remember { mutableStateOf("") }
    val currentUserId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    LaunchedEffect(Unit) {
        if (currentUserId.isNotEmpty()) {
            val snapshot = FirebaseFirestore.getInstance()
                .collection("users")
                .document(currentUserId)
                .get()
                .await()
            userRole = snapshot.getString("role") ?: "Student"
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            BurgerStackMenuLayout(
                onSettingsClick = {
                    scope.launch { drawerState.close() }
                    navController.navigate(Routes.SETTINGS_SCREEN)
                },
                onLogoutClick = {
                    FirebaseAuth.getInstance().signOut()

                    val context = navController.context
                    val sharedPrefs = context.getSharedPreferences(
                        "user_session",
                        Context.MODE_PRIVATE
                    )

                    sharedPrefs.edit()
                        .putBoolean("is_logged_in", false)
                        .apply()

                    scope.launch { drawerState.close() }

                    navController.navigate(Routes.LOGIN_SCREEN) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopBarLayout(onClick = { scope.launch { drawerState.open() } })
            },
            bottomBar = { BottomBarLayout(navController = navController, selectionIdentifier = 1) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(start = 20.dp, end = 30.dp, top = 15.dp, bottom = 5.dp)
                                .size(40.dp),
                            imageVector = Icons.Filled.Announcement,
                            contentDescription = "Announcement Icon",
                            tint = MaterialTheme.colorScheme.primary
                        )

                        EmboldenedTextTemplate(
                            modifier = Modifier.padding(top = 15.dp, bottom = 5.dp),
                            text = "University Announcements"
                        )
                    }

                    HorizontalDivider(
                        modifier = Modifier.padding(10.dp),
                        thickness = 3.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                if (userRole == "Admin") {
                    ButtonTemplate(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        onClick = {
                            newAnnouncementTitle = ""
                            newAnnouncementContent = ""
                            showAddDialog = true
                        },
                        buttonText = "Post New Announcement"
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
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.primary
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
                                            "All" -> viewModel.setFilter(com.example.itew4_casestudy.presentation.viewmodel.AnnouncementFilter.ALL)
                                            "Read" -> viewModel.setFilter(com.example.itew4_casestudy.presentation.viewmodel.AnnouncementFilter.READ)
                                            "Unread" -> viewModel.setFilter(com.example.itew4_casestudy.presentation.viewmodel.AnnouncementFilter.UNREAD)
                                        }
                                    }
                                )
                            }
                        }
                    }
                }

                if (viewModel.announcements.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        NormalTextTemplate(
                            text = "No Announcements Posted",
                            color = Color.Gray,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(viewModel.announcements) { announcement ->
                            val isRead = announcement.readBy.contains(currentUserId)

                            CardTemplate(
                                modifier = Modifier.padding(10.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surface
                                ),
                                innerComposable = {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Start,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
                                                .size(40.dp),
                                            imageVector = Icons.Filled.Campaign,
                                            contentDescription = "Announcement Icon",
                                            tint = MaterialTheme.colorScheme.primary
                                        )

                                        EmboldenedTextTemplate(
                                            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                                            text = announcement.title,
                                            fontSize = 20.sp
                                        )
                                    }

                                    HorizontalDivider(
                                        modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
                                        thickness = 3.dp,
                                        color = MaterialTheme.colorScheme.primary
                                    )

                                    if (!isRead) {
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
                                                .padding(start = 10.dp, end = 10.dp, bottom = 20.dp),
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.End
                                        ) {
                                            ButtonTemplate(
                                                onClick = { viewModel.markAsRead(announcement.id) },
                                                buttonText = "Mark as read",
                                                fontSize = 13.sp
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

            if (showAddDialog) {
                AlertDialog(
                    onDismissRequest = { showAddDialog = false },
                    title = { EmboldenedTextTemplate(text = "New Announcement") },
                    text = {
                        Column {
                            TextFieldTemplate(
                                value = newAnnouncementTitle,
                                valueUpdate = { newAnnouncementTitle = it },
                                textFieldLabel = "Announcement Title",
                                icon = Icons.Filled.Title
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            TextFieldTemplate(
                                value = newAnnouncementContent,
                                valueUpdate = { newAnnouncementContent = it },
                                textFieldLabel = "Announcement Description",
                                icon = Icons.Filled.Description
                            )
                        }
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                if (newAnnouncementTitle.isNotBlank()) {
                                    viewModel.addAnnouncement(newAnnouncementTitle, newAnnouncementContent)
                                    showAddDialog = false
                                }
                            }
                        ) {
                            Text(
                                text = "Post",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showAddDialog = false }) {
                            Text(text = "Cancel", color = Color.Red)
                        }
                    }
                )
            }
        }
    }
}