package com.example.itew4_casestudy.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itew4_casestudy.data.repository.AnnouncementRepository
import com.example.itew4_casestudy.domain.model.AnnouncementModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import kotlinx.coroutines.launch

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

    init {
        listenToAnnouncements()
    }

    fun setFilter(filter: AnnouncementFilter) {
        currentFilter = filter
        applyFilter()
    }

    private fun applyFilter() {
        announcements = when (currentFilter) {
            AnnouncementFilter.ALL -> allAnnouncements
            AnnouncementFilter.READ -> allAnnouncements.filter { it.readBy.contains(currentUserId) }
            AnnouncementFilter.UNREAD -> allAnnouncements.filter { !it.readBy.contains(currentUserId) }
        }
    }

    private fun listenToAnnouncements() {
        repository.getCollectionReference()
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (snapshot != null) {
                    allAnnouncements = snapshot.documents.mapNotNull {
                        it.toObject(AnnouncementModel::class.java)
                    }
                    applyFilter()
                }
            }
    }

    fun addAnnouncement(title: String, content: String) {
        viewModelScope.launch {
            val newAnnouncement = AnnouncementModel(
                title = title,
                content = content,
                timestamp = System.currentTimeMillis(),
                authorId = currentUserId
            )
            repository.addAnnouncement(newAnnouncement)
        }
    }

    fun markAsRead(id: String) {
        val uid = currentUserId
        if (uid.isNotEmpty()) {
            viewModelScope.launch { repository.markAsRead(id, uid) }
        }
    }
}