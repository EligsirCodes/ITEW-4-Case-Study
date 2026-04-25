package com.example.itew4_casestudy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itew4_casestudy.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed class RegistrationState {
    object Idle : RegistrationState()
    object Loading : RegistrationState()
    object Success : RegistrationState()
    data class Error(val message: String) : RegistrationState()
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val role: String) : LoginState()
    data class Error(val message: String) : LoginState()
}

class AuthViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {
    private val _registrationState = MutableStateFlow<RegistrationState>(RegistrationState.Idle)
    val registrationState: StateFlow<RegistrationState> = _registrationState.asStateFlow()

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun register(email: String, password: String, name: String, role: String) {
        if (email.isBlank() || password.isBlank() || name.isBlank()) {
            _registrationState.value = RegistrationState.Error("All fields must be filled.")
            return
        }

        if (password.length < 6) {
            _registrationState.value =
                RegistrationState.Error("Password must be at least 6 characters.")
            return
        }

        _registrationState.value = RegistrationState.Loading

        viewModelScope.launch {
            val result = repository.registerUser(email, password, name, role)
            _registrationState.value = when (result) {
                is AuthRepository.AuthResult.Success -> RegistrationState.Success
                is AuthRepository.AuthResult.Error -> RegistrationState.Error(result.message)
            }
        }
    }

}
