package com.example.itew4_casestudy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.itew4_casestudy.domain.model.TaskModel
import com.example.itew4_casestudy.data.repository.TaskRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import android.content.Context
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskViewModel(
    private val repository: TaskRepository = TaskRepository()
) : ViewModel() {

    var tasks by mutableStateOf<List<TaskModel>>(emptyList())
        private set

    private var snapshotListener: ListenerRegistration? = null

    private val currentUserId: String
        get() = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    init {
        FirebaseAuth.getInstance().addAuthStateListener { auth ->
            if (auth.currentUser != null) {
                listenToTasks()
            } else {
                tasks = emptyList()
                snapshotListener?.remove()
            }
        }
    }

    private fun listenToTasks() {
        val uid = currentUserId
        if (uid.isEmpty()) return

        snapshotListener?.remove()

        snapshotListener = repository.getCollectionReference()
            .whereEqualTo("userId", uid)
            .orderBy("dueDateMillis", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) return@addSnapshotListener

                if (snapshot != null) {
                    val fetchedTasks = snapshot.documents.mapNotNull { doc ->
                        doc.toObject(TaskModel::class.java)
                    }
                    tasks = fetchedTasks.toList()
                }
            }
    }

    fun addTask(context: Context, title: String, dueDate: Long) {
        val uid = currentUserId
        if (uid.isNotEmpty()) {
            viewModelScope.launch {
                val docRef = repository.getCollectionReference().document()
                val newTask = TaskModel(
                    id = docRef.id,
                    title = title,
                    dueDateMillis = dueDate,
                    userId = uid
                )

                docRef.set(newTask)
            }
        }
    }
}