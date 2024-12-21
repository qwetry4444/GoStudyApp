package com.example.gostudyapp.core.data.database.repositories

import com.example.gostudyapp.core.data.database.entities.GroupDto
import com.example.gostudyapp.core.data.database.mappers.toDomain
import com.example.gostudyapp.core.data.database.mappers.toDto
import com.example.gostudyapp.core.domain.IRepository.GroupRepository
import com.example.gostudyapp.core.domain.model.Schedule.Group
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GroupRepositoryImpl @Inject constructor (private val firestore: FirebaseFirestore) :
    GroupRepository {
    override suspend fun getGroupById(groupId: String): Group? {
        val document = firestore.collection("groups")
            .document(groupId.toString())
            .get()
            .await()

        return document.toObject(GroupDto::class.java)?.toDomain()
    }

    override suspend fun getAllGroups(): List<Group> {
        return firestore.collection("groups")
            .get()
            .await()
            .documents
            .mapNotNull { it.toObject(GroupDto::class.java)?.toDomain() }
    }

    override suspend fun addGroup(group: Group) {
        firestore.collection("groups")
            .add(group.toDto())
            .await()
    }

    override suspend fun getGroupIdByNumber(groupNumber: String): String {
        return try {
            val querySnapshot = firestore.collection("groups")
                .whereEqualTo("groupNumber", groupNumber)
                .get()
                .await()

            val document = querySnapshot.documents.firstOrNull()
            document?.id ?: throw Exception("Group with number $groupNumber not found")
        } catch (e: Exception) {
            throw Exception("Error fetching group ID: ${e.message}")
        }
    }
}