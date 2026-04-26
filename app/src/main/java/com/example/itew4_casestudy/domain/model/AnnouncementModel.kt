package com.example.itew4_casestudy.domain.model

data class AnnouncementModel(
    val id: String = "",
    val title: String = "",
    val content: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val readBy: List<String> = emptyList(),
    val authorId: String = ""
)