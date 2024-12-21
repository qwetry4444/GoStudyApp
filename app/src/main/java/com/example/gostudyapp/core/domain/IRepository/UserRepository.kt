package com.example.gostudyapp.core.domain.IRepository

interface UserRepository {
    suspend fun updateUserSubgroupID(uid: String, newSubgroupID: String)
}