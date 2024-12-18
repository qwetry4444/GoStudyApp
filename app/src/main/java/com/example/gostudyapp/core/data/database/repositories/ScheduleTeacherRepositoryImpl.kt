package com.example.gostudyapp.core.data.database.repositories

import com.example.gostudyapp.core.data.database.entities.ScheduleTeacherDto
import com.example.gostudyapp.core.data.database.mappers.toDomain
import com.example.gostudyapp.core.data.database.mappers.toDto
import com.example.gostudyapp.core.domain.IRepository.ScheduleTeacherRepository
import com.example.gostudyapp.core.domain.model.Schedule.ScheduleTeacher
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleTeacherRepositoryImpl @Inject constructor(private val firestore: FirebaseFirestore) :
    ScheduleTeacherRepository {

    override suspend fun getScheduleTeacherById(scheduleTeacherId: String): ScheduleTeacher? {
        return try {
            val document = firestore.collection("schedule_teachers")
                .document(scheduleTeacherId)
                .get()
                .await()

            document.toObject(ScheduleTeacherDto::class.java)?.toDomain()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getAllScheduleTeachers(): List<ScheduleTeacher> {
        return try {
            firestore.collection("schedule_teachers")
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(ScheduleTeacherDto::class.java)?.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun addScheduleTeacher(scheduleTeacher: ScheduleTeacher) {
        try {
            firestore.collection("schedule_teachers")
                .add(scheduleTeacher.toDto())
                .await()
        } catch (_: Exception) { }
    }
}