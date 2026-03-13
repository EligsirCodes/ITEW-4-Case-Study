package com.example.itew4_casestudy.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itew4_casestudy.data.local.AnnouncementEntity
import com.example.itew4_casestudy.data.repository.AnnouncementRepository
import kotlinx.coroutines.launch

enum class AnnouncementFilter {
    ALL,
    READ,
    UNREAD
}

class AnnouncementViewModel(
    private val repository: AnnouncementRepository
) : ViewModel() {
    var announcements by mutableStateOf<List<AnnouncementEntity>>(emptyList())
        private set
    var currentFilter by mutableStateOf(AnnouncementFilter.ALL)
        private set

    init {
        loadAnnouncements()
    }

    fun setFilter(filter: AnnouncementFilter) {
        currentFilter = filter
        loadAnnouncements()
    }

    private fun loadAnnouncements() {
        viewModelScope.launch {
            announcements = when (currentFilter) {
                AnnouncementFilter.ALL -> repository.getAllAnnouncements()
                AnnouncementFilter.READ -> repository.getReadAnnouncements()
                AnnouncementFilter.UNREAD -> repository.getUnreadAnnouncements()
            }
        }
    }

    fun markAsRead(id: Int) {
        viewModelScope.launch {
            repository.markAsRead(id)
            loadAnnouncements()
        }
    }
    fun baseAnnouncements() {
        viewModelScope.launch {
            val existing = repository.getAllAnnouncements()

            if (existing.isEmpty()) {
                repository.insertAnnouncement(
                    AnnouncementEntity(
                        title = "Unread Announcement Example",
                        content = "This announcement is initialized as unread."
                    )
                )

                repository.insertAnnouncement(
                    AnnouncementEntity(
                        title = "Unread Announcement Example",
                        content = "This announcement is initialized as unread."
                    )
                )

                repository.insertAnnouncement(
                    AnnouncementEntity(
                        title = "Read Announcement Example",
                        content = "This announcement is initialized as read.",
                        isRead = true
                    )
                )

                loadAnnouncements()
            }
        }
    }
}