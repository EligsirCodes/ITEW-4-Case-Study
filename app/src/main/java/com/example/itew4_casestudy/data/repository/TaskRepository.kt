package com.example.itew4_casestudy.data.repository

import com.example.itew4_casestudy.domain.model.TaskModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class TaskRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val tasksCollection = firestore.collection("tasks")

    suspend fun addTask(task: TaskModel) {
        val document = tasksCollection.document()
        val newTask = task.copy(id = document.id)
        document.set(newTask).await()
    }

    suspend fun updateTask(task: TaskModel) {
        if (task.id.isNotBlank()) {
            tasksCollection.document(task.id).set(task).await()
        }
    }

    suspend fun deleteTask(taskId: String) {
        if (taskId.isNotBlank()) {
            tasksCollection.document(taskId).delete().await()
        }
    }

    fun getCollectionReference() = tasksCollection
}