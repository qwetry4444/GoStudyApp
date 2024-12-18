package com.example.gostudyapp.core.data.database.repositories

import com.example.gostudyapp.core.data.database.entities.ScheduleDto
import com.example.gostudyapp.core.data.database.mappers.toDomain
import com.example.gostudyapp.core.data.database.mappers.toDto
import com.example.gostudyapp.core.domain.IRepository.ScheduleRepository
import com.example.gostudyapp.core.domain.model.Schedule.Schedule
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleRepositoryImpl @Inject constructor(private val firestore: FirebaseFirestore) :
    ScheduleRepository {

    override suspend fun getScheduleById(scheduleId: String): Schedule? {
        return try {
            val document = firestore.collection("schedules")
                .document(scheduleId)
                .get()
                .await()

            document.toObject(ScheduleDto::class.java)?.toDomain()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getAllSchedules(): List<Schedule> {
        return try {
            firestore.collection("schedules")
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(ScheduleDto::class.java)?.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun addSchedule(schedule: Schedule) {
        try {
            firestore.collection("schedules")
                .add(schedule.toDto())
                .await()
        } catch (e: Exception) {
            // Handle exception if needed
        }
    }
}