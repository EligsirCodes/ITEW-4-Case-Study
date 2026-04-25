package com.example.itew4_casestudy.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

enum class AnnouncementFilter { ALL, READ, UNREAD }
class AnnouncementViewModel(
    private val repository: AnnouncementRepository = AnnouncementRepository()
) : ViewModel() {
    private var allAnnouncements = listOf<AnnouncementModel>()
    var announcements by mutableStateOf<List<AnnouncementModel>>(emptyList())
        private set
    var currentFilter by mutableStateOf(AnnouncementFilter.ALL)
        private set

    private val currentUserId: String
        get() = FirebaseAuth.getInstance().currentUser?.uid ?: ""
}