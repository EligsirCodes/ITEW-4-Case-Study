package com.example.itew4_casestudy.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AnnouncementDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnnouncement(announcement: AnnouncementEntity)
    @Query("SELECT * FROM announcements ORDER BY id DESC")
    suspend fun getAllAnnouncements(): List<AnnouncementEntity>
    @Query("SELECT * FROM announcements WHERE isRead = 1 ORDER BY id DESC")
    suspend fun getReadAnnouncements(): List<AnnouncementEntity>
    @Query("SELECT * FROM announcements WHERE isRead = 0 ORDER BY id DESC")
    suspend fun getUnreadAnnouncements(): List<AnnouncementEntity>
    @Query("UPDATE announcements SET isRead = 1 WHERE id = :announcementId")
    suspend fun markAsRead(announcementId: Int)
    @Update
    suspend fun updateAnnouncement(announcement: AnnouncementEntity)
}