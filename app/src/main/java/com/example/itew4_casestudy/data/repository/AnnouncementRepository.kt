package com.example.itew4_casestudy.data.repository

import com.example.itew4_casestudy.data.local.AnnouncementDao
import com.example.itew4_casestudy.data.local.AnnouncementEntity

class AnnouncementRepository(
    private val announcementDao: AnnouncementDao
) {
    suspend fun insertAnnouncement(announcement: AnnouncementEntity) {
        announcementDao.insertAnnouncement(announcement)
    }
    suspend fun getAllAnnouncements(): List<AnnouncementEntity> {
        return announcementDao.getAllAnnouncements()
    }
    suspend fun getReadAnnouncements(): List<AnnouncementEntity> {
        return announcementDao.getReadAnnouncements()
    }
    suspend fun getUnreadAnnouncements(): List<AnnouncementEntity> {
        return announcementDao.getUnreadAnnouncements()
    }
    suspend fun markAsRead(id: Int) {
        announcementDao.markAsRead(id)
    }
    suspend fun updateAnnouncement(announcement: AnnouncementEntity) {
        announcementDao.updateAnnouncement(announcement)
    }
}
