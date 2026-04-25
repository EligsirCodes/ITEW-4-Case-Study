package com.example.itew4_casestudy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.itew4_casestudy.domain.model.TaskModel
import com.example.itew4_casestudy.data.repository.TaskRepository

class TaskViewModel(
    private val repository: TaskRepository
) : ViewModel() {
    var tasks by mutableStateOf<List<TaskModel>>(emptyList())
        private set
}