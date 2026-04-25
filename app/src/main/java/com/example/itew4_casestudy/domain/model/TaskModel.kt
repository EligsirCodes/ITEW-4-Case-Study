package com.example.itew4_casestudy.domain.model

import com.google.firebase.firestore.PropertyName

data class TaskModel(
    val id: String = "",
    val title: String = "",
    val dueDateMillis: Long = 0L,

    @get:PropertyName("isCompleted")
    @field:PropertyName("isCompleted")
    val isCompleted: Boolean = false,

    val userId: String = ""
)