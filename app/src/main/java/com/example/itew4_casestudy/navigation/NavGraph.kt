package com.example.itew4_casestudy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN_SCREEN
    ) {
        composable(Routes.LOGIN_SCREEN) {

        }

        composable(Routes.REGISTRATION_SCREEN) {

        }

        composable(Routes.DASHBOARD_SCREEN) {

        }

        composable(Routes.INFORMATION_SCREEN) {

        }

        composable(Routes.TASK_SCREEN) {

        }

        composable(Routes.ANNOUNCEMENT_SCREEN) {

        }

        composable(Routes.SETTINGS_SCREEN) {

        }
    }
}