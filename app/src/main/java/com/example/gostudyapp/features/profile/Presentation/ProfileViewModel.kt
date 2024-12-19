package com.example.gostudyapp.features.profile.Presentation

import androidx.lifecycle.viewModelScope
import com.example.gostudyapp.core.data.auth.impl.AccountService
import com.example.gostudyapp.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val accountService: AccountService
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(ProfileState())
    val uiState = _uiState.asStateFlow()

    init {
        loadUserData()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            try {
                val userData = accountService.getUserData()
                val email = userData?.get("email") as? String ?: "Не указано"
                val groupNumber = userData?.get("group_number") as? String ?: "Не указано"
                _uiState.value = ProfileState(userLogin = email, groupNumber = groupNumber)
            } catch (e: Exception) {
                _uiState.value = ProfileState()
            }
        }
    }

    fun signOut() {
        accountService.signOut()
    }
}