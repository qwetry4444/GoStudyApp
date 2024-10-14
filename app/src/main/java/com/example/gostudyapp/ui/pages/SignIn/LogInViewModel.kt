package com.example.gostudyapp.ui.pages.SignIn

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LogInViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LogInState())
    val uiState = _uiState.asStateFlow()

    fun onEmailInputChanged(value: String){
        _uiState.update { currentState ->
            currentState.copy(currentEmail = value) }
    }

    fun onPasswordInputChanged(value: String){
        _uiState.update { currentState ->
            currentState.copy(currentPassword = value) }
    }


}