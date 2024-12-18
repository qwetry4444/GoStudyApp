package com.example.gostudyapp.core.data.database.repositories

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
}
