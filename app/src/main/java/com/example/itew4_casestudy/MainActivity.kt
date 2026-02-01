package com.example.itew4_casestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.itew4_casestudy.navigation.AppNavHost
import com.example.itew4_casestudy.ui.theme.ITEW4CaseStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ITEW4CaseStudyTheme {
                val navController = rememberNavController()

                AppNavHost(navController)
            }
        }
    }
}