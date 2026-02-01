package com.example.itew4_casestudy.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.itew4_casestudy.presentation.screens.AnnouncementsScreen
import com.example.itew4_casestudy.presentation.screens.DashboardScreen
import com.example.itew4_casestudy.presentation.screens.InformationScreen
import com.example.itew4_casestudy.presentation.screens.LoginScreen
import com.example.itew4_casestudy.presentation.screens.RegistrationScreen
import com.example.itew4_casestudy.presentation.screens.SettingsScreen
import com.example.itew4_casestudy.presentation.screens.TaskScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPrefs = context.getSharedPreferences(
        "user_session",
        Context.MODE_PRIVATE
    )

    val isLoggedIn = sharedPrefs.getBoolean("is_logged_in", false)

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn)
            Routes.DASHBOARD_SCREEN
        else
            Routes.LOGIN_SCREEN
    ) {
        composable(Routes.LOGIN_SCREEN) {
            LoginScreen(navController)
        }

        composable(Routes.REGISTRATION_SCREEN) {
            RegistrationScreen(navController)
        }

        composable(Routes.DASHBOARD_SCREEN) {
            DashboardScreen(navController)
        }

        composable(Routes.INFORMATION_SCREEN) {
            InformationScreen(navController)
        }

        composable(Routes.TASK_SCREEN) {
            TaskScreen(navController)
        }

        composable(Routes.ANNOUNCEMENT_SCREEN) {
            AnnouncementsScreen(navController)
        }

        composable(Routes.SETTINGS_SCREEN) {
            SettingsScreen(navController)
        }
    }
}