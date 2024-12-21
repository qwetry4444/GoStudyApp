package com.example.gostudyapp.core.data.database.repositories

import com.example.gostudyapp.core.domain.IRepository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val firestore: FirebaseFirestore) : UserRepository {
    override suspend fun updateUserSubgroupID(uid: String, newSubgroupID: String) {
        try {
            val querySnapshot = firestore.collection("users")
                .whereEqualTo("uid", uid)
                .get()
                .await()

            val document = querySnapshot.documents.firstOrNull()
                ?: throw Exception("User with uid $uid not found")

            firestore.collection("users")
                .document(document.id)
                .update("subgroup_id", newSubgroupID)
                .await()

            println("User subgroupID updated successfully for uid: $uid")
        } catch (e: Exception) {
            println("Error updating subgroupID for uid $uid: ${e.message}")
        }
    }
}
