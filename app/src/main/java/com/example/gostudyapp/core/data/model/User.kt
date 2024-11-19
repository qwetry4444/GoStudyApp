package com.example.gostudyapp.core.data.model

data class User(
    val id: Int,
    val email: String = "",
    val provider: String = "",
    val displayName: String = "",
    val groupNumber: String = ""
)