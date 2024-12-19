package com.example.gostudyapp.features.auth.presentation.SignUp

import com.example.gostudyapp.core.data.auth.impl.AccountService
import com.example.gostudyapp.core.presentation.BaseViewModel
import com.example.gostudyapp.core.presentation.navigation.Route
import com.example.gostudyapp.features.auth.domain.isValidEmail
import com.example.gostudyapp.features.auth.domain.isValidGroupNumber
import com.example.gostudyapp.features.auth.domain.isValidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService
) : BaseViewModel() {
    private val _uiState = MutableStateFlow(SignUpState())
    val uiState = _uiState.asStateFlow()

    fun onEmailInputChanged(value: String){
        _uiState.update { currentState ->
            currentState.copy(currentEmail = value) }
    }

    fun onPasswordInputChanged(value: String){
        _uiState.update { currentState ->
            currentState.copy(currentPassword = value) }
    }

    fun onConfirmPasswordInputChanged(value: String){
        _uiState.update { currentState ->
            currentState.copy(currentConfirmPassword = value) }
    }

    fun onGroupNumberInputChanged(value: String){
        _uiState.update { currentState ->
            currentState.copy(currentGroupNumber = value) }
    }

    fun onSignUpClick(navigateOnSuccess: (Route, Route) -> Unit) {
        launchCatching {
            launchCatching {
                if (!uiState.value.currentEmail.isValidEmail()) {
                    throw IllegalArgumentException("Invalid email format")
                }

                if (!uiState.value.currentPassword.isValidPassword()) {
                    throw IllegalArgumentException("Invalid password format")
                }

                if (uiState.value.currentPassword != uiState.value.currentConfirmPassword) {
                    throw IllegalArgumentException("Passwords do not match")
                }

                if (!uiState.value.currentGroupNumber.isValidGroupNumber()) {
                    throw IllegalArgumentException("GroupDto number do not match")
                }

                accountService.createAccount(uiState.value.currentEmail, uiState.value.currentPassword, uiState.value.currentGroupNumber)

                navigateOnSuccess(Route.Home, Route.SignUp)
            }
        }
    }
}