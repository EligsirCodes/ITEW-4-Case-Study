package com.example.itew4_casestudy.presentation.notifications

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class TaskNotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val taskTitle = intent.getStringExtra("TASK_TITLE") ?: "Task Due"
        showNotification(context, taskTitle)
    }

    companion object {
        fun showNotification(context: Context, taskTitle: String) {
            val sharedPrefs = context.getSharedPreferences("user_settings", Context.MODE_PRIVATE)
            val isEnabled = sharedPrefs.getBoolean("notifications_enabled", true)

            if (!isEnabled) return

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val notification = NotificationCompat.Builder(context, "TASK_CHANNEL")
                .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                .setContentTitle("Task Reminder")
                .setContentText("Your task '$taskTitle' is due in less than 1 hour!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .build()

            notificationManager.notify(taskTitle.hashCode(), notification)
        }
    }
}