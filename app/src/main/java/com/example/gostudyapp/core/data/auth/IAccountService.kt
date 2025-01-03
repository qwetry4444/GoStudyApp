package com.example.gostudyapp.core.data.auth

import com.example.gostudyapp.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IAccountService {
    val currentUser: Flow<User?>
    val currentUserId: String
    fun hasUser(): Boolean
    fun getUserProfile(): User
    suspend fun createAnonymousAccount()
    suspend fun updateDisplayName(newDisplayName: String)
    suspend fun linkAccountWithGoogle(idToken: String)
    suspend fun createAccount(email: String, password: String, groupNumber: String)
    suspend fun signInWithGoogle(idToken: String)
    suspend fun signInWithEmail(email: String, password: String)
    suspend fun signOut()
    suspend fun deleteAccount()
}