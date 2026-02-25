package com.example.itew4_casestudy.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AnnouncementDao {
    // Insert announcement
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnnouncement(announcement: AnnouncementEntity)

    // Get all announcements
    @Query("SELECT * FROM announcements ORDER BY id DESC")
    suspend fun getAllAnnouncements(): List<AnnouncementEntity>

    // Get all READ announcements
    @Query("SELECT * FROM announcements WHERE isRead = 1 ORDER BY id DESC")
    suspend fun getReadAnnouncements(): List<AnnouncementEntity>
}