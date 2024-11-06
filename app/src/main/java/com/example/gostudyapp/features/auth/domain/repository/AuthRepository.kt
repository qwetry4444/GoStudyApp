package com.example.gostudyapp.features.auth.domain.repository

interface AuthRepository {
    fun login(email: String, password: String) : Boolean
    fun signUp(email: String, password: String) : Boolean
    fun signOut()
}