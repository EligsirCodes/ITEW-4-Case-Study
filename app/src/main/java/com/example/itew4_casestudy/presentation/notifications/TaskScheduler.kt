package com.example.itew4_casestudy.presentation.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.itew4_casestudy.domain.model.TaskModel

object TaskScheduler {
    fun scheduleNotification(context: Context, task: TaskModel) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val currentTime = System.currentTimeMillis()
        val triggerTime = task.dueDateMillis - 3600000

        if (currentTime >= triggerTime && currentTime < task.dueDateMillis) {
            TaskNotificationReceiver.showNotification(context, task.title)
            return
        }

        if (task.dueDateMillis <= currentTime) {
            return
        }

        val intent = Intent(context, TaskNotificationReceiver::class.java).apply {
            putExtra("TASK_TITLE", task.title)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            task.id.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
        }
    }

    fun cancelNotification(context: Context, task: TaskModel) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, TaskNotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            task.id.hashCode(),
            intent,
            PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
        )
        pendingIntent?.let { alarmManager.cancel(it) }
    }
}