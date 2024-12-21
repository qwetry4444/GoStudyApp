package com.example.gostudyapp.core.data.database.repositories

import com.example.gostudyapp.core.data.database.entities.ScheduleDto
import com.example.gostudyapp.core.data.database.mappers.toDomain
import com.example.gostudyapp.core.data.database.mappers.toDto
import com.example.gostudyapp.core.domain.IRepository.ScheduleRepository
import com.example.gostudyapp.core.domain.model.Schedule.Room
import com.example.gostudyapp.core.domain.model.Schedule.Schedule
import com.example.gostudyapp.core.domain.model.Schedule.ScheduleWithDetails
import com.example.gostudyapp.core.domain.model.Schedule.Subject
import com.example.gostudyapp.core.domain.model.Schedule.Teacher
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
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
        } catch (e: Exception) { }
    }


    private val scheduleSubgroupsCollection = firestore.collection("schedule_subgroups")

    override suspend fun getScheduleForSubgroup(subgroupID: String): List<ScheduleDto> {
        return try {
            val querySnapshot = scheduleSubgroupsCollection
                .whereEqualTo("subgroupID", subgroupID)
                .get()
                .await()

            val scheduleIds = querySnapshot.documents.map { it.getString("scheduleID") }

            val schedules = scheduleIds.mapNotNull { scheduleId ->
                schedulesCollection.document(scheduleId ?: "").get().await().toObject(ScheduleDto::class.java)
            }

            schedules
        } catch (e: Exception) {
            emptyList()
        }
    }

    private val schedulesCollection = firestore.collection("schedules")
    private val subjectsCollection = firestore.collection("subjects")
    private val teachersCollection = firestore.collection("teachers")
    private val subgroupsCollection = firestore.collection("subgroups")
    private val roomsCollection = firestore.collection("rooms")

    override suspend fun getScheduleWithDetails(scheduleID: String): ScheduleWithDetails = coroutineScope {
        try {
            val scheduleDocDeferred = async {
                schedulesCollection.document(scheduleID).get().await()
            }

            val schedule = scheduleDocDeferred.await().toObject(ScheduleDto::class.java)
                ?: throw Exception("Schedule not found")

            val subjectDeferred = async {
                subjectsCollection.document(schedule.subjectID).get().await()
                    .toObject(Subject::class.java) ?: throw Exception("Subject not found")
            }

            val teachersDeferred = async {
                firestore.collection("schedule_teachers")
                    .whereEqualTo("scheduleID", scheduleID).get().await()
                    .documents.mapNotNull { teacherDoc ->
                        teacherDoc.getString("teacherID")?.let { teacherID ->
                            teachersCollection.document(teacherID).get().await()
                                .toObject(Teacher::class.java)
                        }
                    }
            }

            val subgroupsDeferred = async {
                firestore.collection("schedule_subgroups")
                    .whereEqualTo("scheduleID", scheduleID).get().await()
                    .documents.mapNotNull { it.getString("subgroupID") }
            }

            val roomDeferred = async {
                roomsCollection.document(schedule.roomID).get().await()
                    .toObject(Room::class.java) ?: throw Exception("Room not found")
            }

            val subject = subjectDeferred.await()
            val teachers = teachersDeferred.await()
            val subgroups = subgroupsDeferred.await()
            val room = roomDeferred.await()

            val groups = subgroups.mapNotNull { subgroupID ->
                subgroupsCollection.document(subgroupID).get().await().getString("groupID")
            }

            val isEvenWeek = schedule.weekType == "Even"
            val isOddWeek = schedule.weekType == "Odd"

            ScheduleWithDetails(
                weekday = schedule.weekday.toString(),
                number = schedule.number,
                subject = subject,
                teachers = teachers,
                groups = groups,
                room = room,
                isEvenWeek = isEvenWeek,
                isOddWeek = isOddWeek,
                subgroups = subgroups
            )
        } catch (e: Exception) {
            throw Exception("Failed to fetch schedule details: ${e.message}")
        }
    }

    override suspend fun getScheduleWithDetailsForSubgroup(subgroupID: String): List<ScheduleWithDetails> {
        return try {
            val scheduleDocs = firestore.collection("schedule_subgroups")
                .whereEqualTo("subgroupID", subgroupID).get().await()

            val scheduleIDs = scheduleDocs.documents.mapNotNull { it.getString("scheduleID") }

            scheduleIDs.map { scheduleID ->
                getScheduleWithDetails(scheduleID)
            }
        } catch (e: Exception) {
            throw Exception("Failed to fetch schedules for subgroup: ${e.message}")
        }
    }
}