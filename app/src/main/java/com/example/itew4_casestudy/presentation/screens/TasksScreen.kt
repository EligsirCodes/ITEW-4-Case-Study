package com.example.itew4_casestudy.presentation.screens

import android.app.*
import android.content.Context
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.itew4_casestudy.domain.model.TaskModel
import com.example.itew4_casestudy.navigation.Routes
import com.example.itew4_casestudy.presentation.viewmodel.TaskViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(navController: NavController, viewModel: TaskViewModel) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = navController.context

    var showDialog by remember { mutableStateOf(false) }
    var taskToEdit by remember { mutableStateOf<TaskModel?>(null) }
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
                    val sharedPrefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
                    sharedPrefs.edit().putBoolean("is_logged_in", false).apply()
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
                verticalArrangement = Arrangement.Top,
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
                            tint = MaterialTheme.colorScheme.primary
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
                        color = MaterialTheme.colorScheme.primary
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

                if (viewModel.tasks.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        NormalTextTemplate(
                            text = "No Tasks Posted",
                            color = Color.Gray,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(
                            items = viewModel.tasks,
                            key = { task -> task.id }
                        ) { task ->
                            CardTemplate(
                                modifier = Modifier
                                    .padding(15.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surface
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
                                            color = MaterialTheme.colorScheme.onBackground,
                                            fontSize = 13.sp
                                        )

                                        HorizontalDivider(
                                            modifier = Modifier
                                                .padding(start = 20.dp, end = 20.dp),
                                            thickness = 3.dp,
                                            color = MaterialTheme.colorScheme.primary
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
                                                        viewModel.markAsDone(context, task)
                                                    }
                                                ) {
                                                    Icon(
                                                        modifier = Modifier
                                                            .size(30.dp),
                                                        imageVector = Icons.Outlined.CheckCircle,
                                                        contentDescription = "Done",
                                                        tint = MaterialTheme.colorScheme.primary
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
                                                        tint = MaterialTheme.colorScheme.primary)
                                                }
                                            }

                                            IconButton(
                                                modifier = Modifier
                                                    .padding(start = 15.dp, end = 15.dp),
                                                onClick = {
                                                    viewModel.deleteTask(context, task)
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
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 12.sp
                            )
                        }
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                if (taskToEdit == null) {
                                    viewModel.addTask(context, taskTitle, selectedTimestamp)
                                } else {
                                    viewModel.updateTask(
                                        context,
                                        taskToEdit!!.copy(title = taskTitle, dueDateMillis = selectedTimestamp)
                                    )
                                }
                                showDialog = false
                            }
                        ) {
                            Text(
                                text = "Save",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
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