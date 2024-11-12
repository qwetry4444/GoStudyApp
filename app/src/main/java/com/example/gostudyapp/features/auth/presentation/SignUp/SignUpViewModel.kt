package com.example.gostudyapp.features.auth.presentation.SignUp

import androidx.lifecycle.ViewModel
import com.example.gostudyapp.features.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private var authRepository: AuthRepository
) : ViewModel() {

}