package com.example.itew4_casestudy.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itew4_casestudy.data.local.TaskEntity
import com.example.itew4_casestudy.data.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(
    private val repository: TaskRepository
) : ViewModel() {
    var tasks by mutableStateOf<List<TaskEntity>>(emptyList())
        private set

    init {
        loadTasks()
    }

    fun loadTasks() {
        viewModelScope.launch {
            tasks = repository.getAllTasks()
        }
    }

    fun addTask(title: String, dueDate: Long) {
        viewModelScope.launch {
            repository.insertTask(TaskEntity(title = title, dueDateMillis = dueDate))
            loadTasks()
        }
    }

    fun updateTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.updateTask(task)
            loadTasks()
        }
    }

    fun markAsDone(task: TaskEntity) {
        viewModelScope.launch {
            repository.updateTask(task.copy(isCompleted = true))
            loadTasks()
        }
    }

    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.deleteTask(task)
            loadTasks()
        }
    }
}