package com.example.itew4_casestudy.data.repository

import com.example.itew4_casestudy.data.local.AnnouncementDao
import com.example.itew4_casestudy.data.local.AnnouncementEntity

class AnnouncementRepository(
    private val announcementDao: AnnouncementDao
) {

    // Insert announcement
    suspend fun insertAnnouncement(announcement: AnnouncementEntity) {
        announcementDao.insertAnnouncement(announcement)
    }

    // Get all announcements
    suspend fun getAllAnnouncements(): List<AnnouncementEntity> {
        return announcementDao.getAllAnnouncements()
    }

    // Get read announcements
    suspend fun getReadAnnouncements(): List<AnnouncementEntity> {
        return announcementDao.getReadAnnouncements()
    }

    // Get unread announcements
    suspend fun getUnreadAnnouncements(): List<AnnouncementEntity> {
        return announcementDao.getUnreadAnnouncements()
    }

    // Mark as read
    suspend fun markAsRead(id: Int) {
        announcementDao.markAsRead(id)
    }

    // Update announcement
    suspend fun updateAnnouncement(announcement: AnnouncementEntity) {
        announcementDao.updateAnnouncement(announcement)
    }
}