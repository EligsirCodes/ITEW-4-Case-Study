package com.example.itew4_casestudy.presentation.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.itew4_casestudy.R
import com.example.itew4_casestudy.navigation.Routes
import com.example.itew4_casestudy.presentation.viewmodel.AuthViewModel
import com.example.itew4_casestudy.presentation.viewmodel.LoginState

@Composable
fun LoginScreen(navController: NavController, viewModel: AuthViewModel) {
    val context = LocalContext.current
    val sharedPrefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    val scrollState = rememberScrollState()
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val loginState by viewModel.loginState.collectAsState()

    LaunchedEffect(loginState) {
        when (loginState) {
            is LoginState.Success -> {
                sharedPrefs.edit()
                    .putBoolean("is_logged_in", true)
                    .apply()

                navController.navigate(Routes.DASHBOARD_SCREEN) {
                    popUpTo(Routes.LOGIN_SCREEN) { inclusive = true }
                }
                viewModel.resetLoginState()
            }
            is LoginState.Error -> {
                Toast.makeText(context, (loginState as LoginState.Error).message, Toast.LENGTH_SHORT).show()
                viewModel.resetLoginState()
            }
            else -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircleLayout(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .size(220.dp)
                    .shadow(
                        elevation = 10.dp,
                        shape = CircleShape,
                        clip = false
                    )
                    .border(
                        width = 5.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )
                    .clip(CircleShape),
                innerComposable = {
                    Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = painterResource(R.drawable.pnclogo),
                        contentDescription = "PNC Logo"
                    )
                }
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmboldenedTextTemplate(
                    text = "Welcome to PNC Buddy",
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 5.dp)
                )

                NormalTextTemplate(
                    text = "Log in to your account",
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 15.dp)
                )
            }
        }

        TextFieldLayout(
            layoutLabel = "Email",
            innerComposable = {
                TextFieldTemplate(
                    value = email,
                    valueUpdate = {
                        email = it
                    },
                    textFieldLabel = "Email",
                    icon = Icons.Filled.Person
                )
            }
        )

        TextFieldLayout(
            layoutLabel = "Password",
            innerComposable = {
                PasswordTextFieldTemplate(
                    value = password,
                    valueUpdate = {
                        password = it
                    },
                    textFieldLabel = "Password",
                    leadingIcon = Icons.Filled.Lock
                )
            }
        )

        ButtonTemplate(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
            onClick = {
                viewModel.login(email.trim(), password.trim())
            },
            buttonText = if (loginState is LoginState.Loading) "Logging in..." else "Log In"
        )

        LogInOrSignUpLayout(
            initialText = "Don't have an account? ",
            highlightedText = "Sign Up",
            onClick = {
                navController.navigate(Routes.REGISTRATION_SCREEN)
            }
        )
    }
}