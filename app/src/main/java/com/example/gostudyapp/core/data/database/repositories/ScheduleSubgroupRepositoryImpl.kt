package com.example.gostudyapp.core.data.database.repositories

import com.example.gostudyapp.core.data.database.entities.ScheduleSubgroupDto
import com.example.gostudyapp.core.data.database.mappers.toDomain
import com.example.gostudyapp.core.data.database.mappers.toDto
import com.example.gostudyapp.core.domain.IRepository.ScheduleSubgroupRepository
import com.example.gostudyapp.core.domain.model.Schedule.ScheduleSubgroup
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleSubgroupRepositoryImpl @Inject constructor(private val firestore: FirebaseFirestore) :
    ScheduleSubgroupRepository {

    override suspend fun getScheduleSubgroupById(scheduleSubgroupId: String): ScheduleSubgroup? {
        return try {
            val document = firestore.collection("schedule_subgroups")
                .document(scheduleSubgroupId)
                .get()
                .await()

            document.toObject(ScheduleSubgroupDto::class.java)?.toDomain()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getAllScheduleSubgroups(): List<ScheduleSubgroup> {
        return try {
            firestore.collection("schedule_subgroups")
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(ScheduleSubgroupDto::class.java)?.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun addScheduleSubgroup(scheduleSubgroup: ScheduleSubgroup) {
        try {
            firestore.collection("schedule_subgroups")
                .add(scheduleSubgroup.toDto())
                .await()
        } catch (e: Exception) {
            // Handle exception if needed
        }
    }
}