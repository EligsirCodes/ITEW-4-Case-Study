package com.example.itew4_casestudy.presentation.screens

import android.app.*
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.itew4_casestudy.data.local.TaskEntity
import com.example.itew4_casestudy.navigation.Routes
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = navController.context

    val database =
        remember { com.example.itew4_casestudy.data.local.AppDatabase.getDatabase(context) }
    val repository =
        remember { com.example.itew4_casestudy.data.repository.TaskRepository(database.taskDao()) }
    val factory = remember {
        com.example.itew4_casestudy.presentation.viewmodel.TaskViewModelFactory(repository)
    }
    val viewModel: com.example.itew4_casestudy.presentation.viewmodel.TaskViewModel =
        androidx.lifecycle.viewmodel.compose.viewModel(factory = factory)

    var showDialog by remember { mutableStateOf(false) }
    var taskToEdit by remember { mutableStateOf<TaskEntity?>(null) }
    var taskTitle by remember { mutableStateOf("") }
    var selectedTimestamp by remember { mutableLongStateOf(System.currentTimeMillis()) }

    val sdf = remember { SimpleDateFormat("MMM dd, yyyy - hh:mm a", Locale.getDefault()) }

    fun showDateTimePicker() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            context,
            { _, year, month, day ->
                calendar.set(year, month, day)
                TimePickerDialog(context, { _, hour, minute ->
                    calendar.set(Calendar.HOUR_OF_DAY, hour)
                    calendar.set(Calendar.MINUTE, minute)
                    selectedTimestamp = calendar.timeInMillis
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
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
            bottomBar = { BottomBarLayout( navController = navController, selectionIdentifier = 3) }
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
                            imageVector = Icons.Filled.Task,
                            contentDescription = "Task Icon",
                            tint = Color(red = 13, green = 61, blue = 3)
                        )

                        EmboldenedTextTemplate(
                            modifier = Modifier
                                .padding(top = 15.dp, bottom = 5.dp),
                            text = "Tasks"
                        )
                    }

                    HorizontalDivider(
                        modifier = Modifier
                            .padding(10.dp),
                        thickness = 3.dp,
                        color = Color(red = 13, green = 61, blue = 3)
                    )
                }

                ButtonTemplate(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                    onClick = {
                        taskToEdit = null
                        taskTitle = ""
                        selectedTimestamp = System.currentTimeMillis()
                        showDialog = true
                    },
                    buttonText = "Add New Task"
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(viewModel.tasks) { task ->
                        CardTemplate(
                            modifier = Modifier
                                .padding(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(red = 179, green = 204, blue = 175)
                            ),
                            innerComposable = {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    EmboldenedTextTemplate(
                                        modifier = Modifier.
                                            padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 5.dp),
                                        text = task.title,
                                    )

                                    NormalTextTemplate(
                                        modifier = Modifier.
                                            padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
                                        text = "Due: ${sdf.format(Date(task.dueDateMillis))}",
                                        fontSize = 13.sp
                                    )

                                    HorizontalDivider(
                                        modifier = Modifier
                                            .padding(start = 20.dp, end = 20.dp),
                                        thickness = 3.dp,
                                        color = Color(red = 13, green = 61, blue = 3)
                                    )

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 20.dp, end = 20.dp, bottom = 5.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        if (!task.isCompleted) {
                                            IconButton(
                                                modifier = Modifier
                                                    .padding(start = 15.dp, end = 15.dp),
                                                onClick = {
                                                    viewModel.markAsDone(task)
                                                }
                                            ) {
                                                Icon(
                                                    modifier = Modifier
                                                        .size(30.dp),
                                                    imageVector = Icons.Outlined.CheckCircle,
                                                    contentDescription = "Done",
                                                    tint = Color(red = 13, green = 61, blue = 3)
                                                )
                                            }

                                            IconButton(
                                                modifier = Modifier
                                                    .padding(start = 15.dp, end = 15.dp),
                                                onClick = {
                                                    taskToEdit = task
                                                    taskTitle = task.title
                                                    selectedTimestamp = task.dueDateMillis
                                                    showDialog = true
                                                }
                                            ) {
                                                Icon(
                                                    modifier = Modifier
                                                        .size(30.dp),
                                                    imageVector = Icons.Outlined.Edit,
                                                    contentDescription = "Edit",
                                                    tint = Color(red = 13, green = 61, blue = 3))
                                            }
                                        }

                                        IconButton(
                                            modifier = Modifier
                                                .padding(start = 15.dp, end = 15.dp),
                                            onClick = {
                                                viewModel.deleteTask(task)
                                            }
                                        ) {
                                            Icon(
                                                modifier = Modifier
                                                    .size(30.dp),
                                                imageVector = Icons.Outlined.Delete,
                                                contentDescription = "Delete",
                                                tint = Color.Red
                                            )
                                        }
                                    }
                                }
                            }
                        )
                    }
                }
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = {
                        showDialog = false
                    },
                    title = {
                        EmboldenedTextTemplate(
                            text = if (taskToEdit == null) "New Task" else "Edit Task"
                        )
                    },
                    text = {
                        Column {
                            TextFieldTemplate(
                                value = taskTitle,
                                valueUpdate = { taskTitle = it },
                                textFieldLabel = "Task Title",
                                icon = Icons.Filled.Title
                            )

                            ButtonTemplate(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
                                onClick = {
                                    showDateTimePicker()
                                },
                                buttonText = "Set Date & Time"
                            )

                            NormalTextTemplate(
                                modifier = Modifier.
                                    padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
                                text = "Selected: ${sdf.format(Date(selectedTimestamp))}",
                                fontSize = 12.sp
                            )
                        }
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                if (taskToEdit == null) {
                                    viewModel.addTask(taskTitle, selectedTimestamp)
                                } else {
                                    viewModel.updateTask(
                                        taskToEdit!!.copy(
                                            title = taskTitle,
                                            dueDateMillis = selectedTimestamp
                                        )
                                    )
                                }
                                showDialog = false
                            }
                        ) {
                            Text("Save", color = Color(13, 61, 3), fontWeight = FontWeight.Bold)
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = { showDialog = false }
                        ) {
                            Text(
                                text = "Cancel",
                                color = Color.Red
                            )
                        }
                    }
                )
            }
        }
    }
}