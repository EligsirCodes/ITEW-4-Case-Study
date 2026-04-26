package com.example.itew4_casestudy.presentation.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.itew4_casestudy.R
import com.example.itew4_casestudy.navigation.Routes
import com.example.itew4_casestudy.presentation.viewmodel.AuthViewModel
import com.example.itew4_casestudy.presentation.viewmodel.RegistrationState
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(navController: NavController, viewModel: AuthViewModel) {
    val scrollState = rememberScrollState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var email by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var selectedRole by rememberSaveable { mutableStateOf("Student") }
    val registrationState by viewModel.registrationState.collectAsState()

    LaunchedEffect(registrationState) {
        when (registrationState) {
            is RegistrationState.Success -> {
                navController.navigate(Routes.LOGIN_SCREEN) {
                    popUpTo(Routes.REGISTRATION_SCREEN) { inclusive = true }
                }
                viewModel.resetState()
            }
            is RegistrationState.Error -> {
                scope.launch {
                    snackbarHostState.showSnackbar((registrationState as RegistrationState.Error).message)
                }
                viewModel.resetState()
            }
            else -> {}
        }
    }

    val indicatorBias by animateFloatAsState(
        targetValue = if (selectedRole == "Student") -1f else 1f,
        animationSpec = spring(stiffness = 300f),
        label = "RoleIndicatorAnimation"
    )

    val studentTextColor by animateColorAsState(
        targetValue = if (selectedRole == "Student") MaterialTheme.colorScheme.primary else Color.White,
        label = "StudentTextColor"
    )
    val adminTextColor by animateColorAsState(
        targetValue = if (selectedRole == "Admin") MaterialTheme.colorScheme.primary else Color.White,
        label = "AdminTextColor"
    )

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircleLayout(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .size(70.dp)
                        .shadow(elevation = 10.dp, shape = CircleShape, clip = false)
                        .border(width = 3.dp, color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                        .clip(CircleShape),
                    innerComposable = {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(R.drawable.pnclogo),
                            contentDescription = "PNC Logo"
                        )
                    }
                )

                Column(
                    modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    EmboldenedTextTemplate(
                        text = "Sign Up Now",
                        modifier = Modifier.padding(top = 15.dp, bottom = 15.dp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(top = 15.dp, bottom = 5.dp)
                    .height(50.dp)
                    .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                    .padding(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight()
                        .align(BiasAlignment(horizontalBias = indicatorBias, verticalBias = 0f))
                        .background(Color.White, CircleShape)
                )

                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.weight(1f).fillMaxHeight().clip(CircleShape).clickable { selectedRole = "Student" },
                        contentAlignment = Alignment.Center
                    ) {
                        EmboldenedTextTemplate(text = "Student", fontSize = 18.sp, color = studentTextColor)
                    }

                    Box(
                        modifier = Modifier.weight(1f).fillMaxHeight().clip(CircleShape).clickable { selectedRole = "Admin" },
                        contentAlignment = Alignment.Center
                    ) {
                        EmboldenedTextTemplate(text = "Admin", fontSize = 18.sp, color = adminTextColor)
                    }
                }
            }

            TextFieldLayout(
                layoutLabel = "Input Email",
                innerComposable = {
                    TextFieldTemplate(
                        value = email,
                        valueUpdate = { email = it },
                        textFieldLabel = "Email",
                        icon = Icons.Filled.Email
                    )
                }
            )

            TextFieldLayout(
                layoutLabel = "Input Full Name",
                innerComposable = {
                    TextFieldTemplate(
                        value = name,
                        valueUpdate = { name = it },
                        textFieldLabel = "Full Name",
                        icon = Icons.Filled.Person
                    )
                }
            )

            TextFieldLayout(
                layoutLabel = "Input Password",
                innerComposable = {
                    PasswordTextFieldTemplate(
                        value = password,
                        valueUpdate = { password = it },
                        textFieldLabel = "Password",
                        leadingIcon = Icons.Filled.Lock
                    )
                }
            )

            TextFieldLayout(
                layoutLabel = "Confirm Password",
                innerComposable = {
                    PasswordTextFieldTemplate(
                        value = confirmPassword,
                        valueUpdate = { confirmPassword = it },
                        textFieldLabel = "Confirm Password",
                        leadingIcon = Icons.Filled.Lock
                    )
                }
            )

            ButtonTemplate(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
                onClick = {
                    val trimmedEmail = email.trim()
                    val trimmedName = name.trim()

                    when {
                        trimmedEmail.isEmpty() -> {
                            scope.launch { snackbarHostState.showSnackbar("Please enter an email.") }
                        }
                        trimmedName.isEmpty() -> {
                            scope.launch { snackbarHostState.showSnackbar("Full Name is required.") }
                        }
                        password.isEmpty() -> {
                            scope.launch { snackbarHostState.showSnackbar("Password is required.") }
                        }
                        password.length < 6 -> {
                            scope.launch { snackbarHostState.showSnackbar("Password must be at least 6 characters.") }
                        }
                        password != confirmPassword -> {
                            scope.launch { snackbarHostState.showSnackbar("Passwords do not match!") }
                        }
                        else -> {
                            viewModel.register(
                                email = trimmedEmail,
                                password = password.trim(),
                                name = trimmedName,
                                role = selectedRole
                            )
                        }
                    }
                },
                buttonText = if (registrationState is RegistrationState.Loading) "Registering..." else "Register"
            )

            LogInOrSignUpLayout(
                initialText = "Already have an account? ",
                highlightedText = "Log in here",
                onClick = {
                    navController.navigate(Routes.LOGIN_SCREEN)
                }
            )
        }
    }
}