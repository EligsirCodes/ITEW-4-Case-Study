package com.example.itew4_casestudy.data.repository

import com.example.itew4_casestudy.data.local.TaskDao
import com.example.itew4_casestudy.data.local.TaskEntity

class TaskRepository(
    private val taskDao: TaskDao
) {
    suspend fun insertTask(task: TaskEntity) {
        taskDao.insertTask(task)
    }

    suspend fun updateTask(task: TaskEntity) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: TaskEntity) {
        taskDao.deleteTask(task)
    }

    suspend fun getAllTasks(): List<TaskEntity> {
        return taskDao.getAllTasks()
    }
}