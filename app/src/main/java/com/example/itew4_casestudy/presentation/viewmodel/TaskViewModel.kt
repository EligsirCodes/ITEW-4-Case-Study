package com.example.itew4_casestudy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.itew4_casestudy.domain.model.TaskModel
import com.example.itew4_casestudy.data.repository.TaskRepository
import com.google.firebase.auth.FirebaseAuth

class TaskViewModel(
    private val repository: TaskRepository
) : ViewModel() {
    var tasks by mutableStateOf<List<TaskModel>>(emptyList())
        private set

    private val currentUserId: String
        get() = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    init {
        FirebaseAuth.getInstance().addAuthStateListener { auth ->
            if (auth.currentUser != null) {
                // will listen later
            } else {
                tasks = emptyList()
            }
        }
    }
}