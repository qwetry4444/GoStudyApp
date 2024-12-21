package com.example.gostudyapp.core.data.database.repositories

import com.example.gostudyapp.core.data.database.entities.GroupDto
import com.example.gostudyapp.core.data.database.entities.SubgroupDto
import com.example.gostudyapp.core.data.database.mappers.toDomain
import com.example.gostudyapp.core.data.database.mappers.toDto
import com.example.gostudyapp.core.domain.IRepository.SubgroupRepository
import com.example.gostudyapp.core.domain.model.Schedule.Subgroup
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SubgroupRepositoryImpl @Inject constructor(private val firestore: FirebaseFirestore) :
    SubgroupRepository {

    override suspend fun getSubgroupById(subgroupId: String): Subgroup? {
        return try {
            val document = firestore.collection("subgroups")
                .document(subgroupId)
                .get()
                .await()

            document.toObject(SubgroupDto::class.java)?.toDomain()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getAllSubgroups(): List<Subgroup> {
        return try {
            firestore.collection("subgroups")
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(SubgroupDto::class.java)?.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun addSubgroup(subgroup: Subgroup) {
        try {
            firestore.collection("subgroups")
                .add(subgroup.toDto())
                .await()
        } catch (e: Exception) {
            // Handle exception if needed
        }
    }

    override suspend fun getSubgroupIdByNumberAndGroupId(
        subgroupNumber: String,
        groupId: String
    ): String {
        return try {
            val querySnapshot = firestore.collection("subgroups")
                .whereEqualTo("subgroupName", subgroupNumber)
                .whereEqualTo("groupID", groupId)
                .get()
                .await()

            val document = querySnapshot.documents.firstOrNull()
            document?.id ?: throw Exception("Subgroup with number $subgroupNumber and group ID $groupId not found")
        } catch (e: Exception) {
            throw Exception("Error fetching subgroup ID: ${e.message}")
        }
    }


    override suspend fun getSubgroupIdByGroupNumberAndSubgroupName(
        groupNumber: String,
        subgroupName: String
    ): String {
        return try {
            val groupQuery = firestore.collection("groups")
                .whereEqualTo("groupNumber", groupNumber)
                .get()
                .await()

            val groupId = groupQuery.documents.firstOrNull()?.id
                ?: throw Exception("Group with number $groupNumber not found")

            val subgroupQuery = firestore.collection("subgroups")
                .whereEqualTo("groupID", groupId)
                .whereEqualTo("subgroupName", subgroupName)
                .get()
                .await()

            val subgroupId = subgroupQuery.documents.firstOrNull()?.id
                ?: throw Exception("Subgroup with name $subgroupName not found for group $groupNumber")

            subgroupId
        } catch (e: Exception) {
            throw Exception("Error fetching subgroup ID: ${e.message}")
        }
    }

    override suspend fun getGroupNumberAndSubgroupName(subgroupId: String): String {
        try {
            val subgroupDocument = firestore.collection("subgroups")
                .document(subgroupId)
                .get()
                .await()

            val subgroupDto = subgroupDocument.toObject(SubgroupDto::class.java)
                ?: throw Exception("Subgroup with ID $subgroupId not found")

            val groupDocument = firestore.collection("groups")
                .document(subgroupDto.groupID)
                .get()
                .await()

            val groupDto = groupDocument.toObject(GroupDto::class.java)
                ?: throw Exception("Group with ID ${subgroupDto.groupID} not found")

            return "${groupDto.groupNumber}${subgroupDto.subgroupName}"
        } catch (e: Exception) {
            println("Error: ${e.message}")
            throw e
        }
    }
}
