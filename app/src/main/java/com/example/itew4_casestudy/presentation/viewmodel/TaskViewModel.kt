package com.example.itew4_casestudy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.itew4_casestudy.data.repository.TaskRepository

class TaskViewModel(
    private val repository: TaskRepository
) : ViewModel() {

}