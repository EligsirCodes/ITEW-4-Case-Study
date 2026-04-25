package com.example.itew4_casestudy.presentation.viewmodel

import androidx.lifecycle.ViewModel

enum class AnnouncementFilter { ALL, READ, UNREAD }
class AnnouncementViewModel(
    private val repository: AnnouncementRepository = AnnouncementRepository()
) : ViewModel() {

}