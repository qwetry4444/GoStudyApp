package com.example.gostudyapp.features.auth.presentation.SignUp

data class SignUpState(
    var currentEmail: String = "",
    var currentPassword: String = "",
    var currentConfirmPassword: String = "",
    var currentGroupNumber: String = "",
)