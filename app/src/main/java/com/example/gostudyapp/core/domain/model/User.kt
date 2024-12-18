package com.example.gostudyapp.core.domain.model

data class User(
    val id: String = "",
    val email: String = "",
    val provider: String = "",
    val displayName: String = "",
    val groupNumber: String = ""
)