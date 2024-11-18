package com.example.gostudyapp.features.auth.presentation.SignIn

import android.util.Log
import androidx.credentials.Credential
import com.example.gostudyapp.core.data.auth.impl.AccountService
import com.example.gostudyapp.core.presentation.BaseViewModel
import com.example.gostudyapp.core.presentation.ERROR_TAG
import com.example.gostudyapp.core.presentation.UNEXPECTED_CREDENTIAL
import com.example.gostudyapp.core.presentation.navigation.Home
import com.example.gostudyapp.core.presentation.navigation.Route
import com.example.gostudyapp.core.presentation.navigation.SignIn
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val accountService: AccountService
) : BaseViewModel()
{
    private val _uiState = MutableStateFlow(SignInState())
    val uiState = _uiState.asStateFlow()

    fun onEmailInputChanged(value: String){
        _uiState.update { currentState ->
            currentState.copy(currentEmail = value) }
    }

    fun onPasswordInputChanged(value: String){
        _uiState.update { currentState ->
            currentState.copy(currentPassword = value) }
    }

    fun onSignInClick(navigateOnSuccess: (Route, Route) -> Unit) {
        launchCatching {
            accountService.signInWithEmail(uiState.value.currentEmail, uiState.value.currentPassword)
            navigateOnSuccess(Home, SignIn)
        }

    }


    fun onSignInWithGoogle(credential: Credential, navigateOnSuccess: (Route, Route) -> Unit) {
        launchCatching {
            if (credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) { //credential is CustomCredential ||
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                accountService.signInWithGoogle(googleIdTokenCredential.idToken)
                navigateOnSuccess(Home, SignIn)

            } else {
                Log.e(ERROR_TAG, UNEXPECTED_CREDENTIAL)
            }
        }
    }
}