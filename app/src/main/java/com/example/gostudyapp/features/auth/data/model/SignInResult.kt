package com.example.gostudyapp.features.auth.data.model

data class SignInResult(
    val userData: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String?,
    //...
)
