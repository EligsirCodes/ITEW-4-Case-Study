package com.example.itew4_casestudy.presentation.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ThemeViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPrefs = application.getSharedPreferences("user_settings", Context.MODE_PRIVATE)
    private val _isDarkMode = MutableStateFlow(sharedPrefs.getBoolean("dark_mode", false))
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()
    private val _isNotificationsEnabled = MutableStateFlow(sharedPrefs.getBoolean("notifications_enabled", true))
    val isNotificationsEnabled: StateFlow<Boolean> = _isNotificationsEnabled.asStateFlow()

    fun toggleDarkMode(isEnabled: Boolean) {
        _isDarkMode.value = isEnabled
        sharedPrefs.edit().putBoolean("dark_mode", isEnabled).apply()
    }

    fun toggleNotifications(isEnabled: Boolean) {
        _isNotificationsEnabled.value = isEnabled
        sharedPrefs.edit().putBoolean("notifications_enabled", isEnabled).apply()
    }
}