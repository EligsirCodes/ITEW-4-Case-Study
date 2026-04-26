package com.example.itew4_casestudy.data.repository

import com.example.itew4_casestudy.domain.model.AnnouncementModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AnnouncementRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val announcementsCollection = firestore.collection("announcements")

    suspend fun addAnnouncement(announcement: AnnouncementModel) {
        val document = announcementsCollection.document()
        val newAnnouncement = announcement.copy(id = document.id)
        document.set(newAnnouncement).await()
    }

    suspend fun markAsRead(announcementId: String, userId: String) {
        if (announcementId.isBlank()) return

        val document = announcementsCollection.document(announcementId)
        document.update("readBy", FieldValue.arrayUnion(userId)).await()
    }

    fun getCollectionReference() = announcementsCollection
}