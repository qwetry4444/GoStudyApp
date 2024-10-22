package com.example.gostudyapp.ui.pages.SignIn

import androidx.lifecycle.ViewModel
import com.example.gostudyapp.AuthViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val authViewModel: AuthViewModel
) : ViewModel() {
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