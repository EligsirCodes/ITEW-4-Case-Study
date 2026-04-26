package com.example.itew4_casestudy

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.itew4_casestudy.domain.model.AnnouncementModel
import com.example.itew4_casestudy.navigation.AppNavHost
import com.example.itew4_casestudy.presentation.viewmodel.ThemeViewModel
import com.example.itew4_casestudy.ui.theme.ITEW4CaseStudyTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    private var isListenerAttached = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        createNotificationChannel()

        setContent {
            val themeViewModel: ThemeViewModel = viewModel()
            val isDarkMode by themeViewModel.isDarkMode.collectAsState()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val permissionLauncher = rememberLauncherForActivityResult(
                    ActivityResultContracts.RequestPermission()
                ) { isGranted ->
                    if (isGranted) { attachGlobalAnnouncementListener() }
                }

                LaunchedEffect(Unit) {
                    if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                        permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    } else {
                        attachGlobalAnnouncementListener()
                    }
                }
            } else {
                LaunchedEffect(Unit) { attachGlobalAnnouncementListener() }
            }

            ITEW4CaseStudyTheme(darkTheme = isDarkMode) {
                val navController = rememberNavController()
                AppNavHost(navController, themeViewModel)
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val annName = "University Announcements"
            val annDesc = "Notifications for new university announcements"
            val annImportance = NotificationManager.IMPORTANCE_DEFAULT
            val annChannel = NotificationChannel("ANNOUNCEMENT_CHANNEL", annName, annImportance).apply {
                description = annDesc
            }

            val taskName = "Task Reminders"
            val taskDesc = "Notifications for upcoming tasks"
            val taskImportance = NotificationManager.IMPORTANCE_HIGH
            val taskChannel = NotificationChannel("TASK_CHANNEL", taskName, taskImportance).apply {
                description = taskDesc
            }

            notificationManager.createNotificationChannel(annChannel)
            notificationManager.createNotificationChannel(taskChannel)
        }
    }

    private fun attachGlobalAnnouncementListener() {
        if (isListenerAttached) return
        isListenerAttached = true

        val db = FirebaseFirestore.getInstance()
        val auth = FirebaseAuth.getInstance()

        val appStartTime = System.currentTimeMillis()

        db.collection("announcements")
            .addSnapshotListener { snapshots, e ->
                if (e != null || snapshots == null) return@addSnapshotListener

                for (dc in snapshots.documentChanges) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        val announcement = dc.document.toObject(AnnouncementModel::class.java)
                        val currentUserId = auth.currentUser?.uid

                        if (announcement.timestamp > appStartTime && announcement.authorId != currentUserId) {
                            showNotification(announcement.title, announcement.content)
                        }
                    }
                }
            }
    }

    private fun showNotification(title: String, content: String) {
        val sharedPrefs = getSharedPreferences("user_settings", Context.MODE_PRIVATE)
        val isEnabled = sharedPrefs.getBoolean("notifications_enabled", true)

        if (!isEnabled) return

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder = NotificationCompat.Builder(this, "ANNOUNCEMENT_CHANNEL")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
    }
}