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


    private val scheduleSubgroupsCollection = firestore.collection("schedule_subgroups")

    override suspend fun getScheduleForSubgroup(subgroupID: String): List<ScheduleDto> {
        return try {
            // Получаем расписания, связанные с подгруппой
            val querySnapshot = scheduleSubgroupsCollection
                .whereEqualTo("subgroupID", subgroupID)
                .get()
                .await()

            // Извлекаем связанные расписания
            val scheduleIds = querySnapshot.documents.map { it.getString("scheduleID") }

            // Получаем сами расписания по идентификаторам
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
    private val groupsCollection = firestore.collection("groups")

    override suspend fun getScheduleWithDetails(scheduleID: String): ScheduleWithDetails {
        return try {
            // 1. Получаем основное расписание
            val scheduleDoc = schedulesCollection.document(scheduleID).get().await()
            val schedule = scheduleDoc.toObject(ScheduleDto::class.java)
                ?: throw Exception("Schedule not found")

            // 2. Получаем предмет
            val subjectDoc = subjectsCollection.document(schedule.subjectID).get().await()
            val subject = subjectDoc.toObject(Subject::class.java)
                ?: throw Exception("Subject not found")

            // 3. Получаем преподавателей
            val teacherDocs = firestore.collection("schedule_teachers")
                .whereEqualTo("scheduleID", scheduleID).get().await()

            val teachers = teacherDocs.documents.mapNotNull { teacherDoc ->
                val teacherID = teacherDoc.getString("teacherID")
                teacherID?.let {
                    teachersCollection.document(it).get().await().toObject(Teacher::class.java)
                }
            }

            // 4. Получаем подгруппы
            val subgroupDocs = firestore.collection("schedule_subgroups")
                .whereEqualTo("scheduleID", scheduleID).get().await()

            val subgroups = subgroupDocs.documents.mapNotNull {
                it.getString("subgroupID")
            }

            // 5. Получаем группы, связанные с подгруппами
            val groups = subgroups.flatMap { subgroupID ->
                val subgroupDoc = subgroupsCollection.document(subgroupID).get().await()
                val groupID = subgroupDoc.getString("groupID")
                groupID?.let { listOf(it) } ?: emptyList()
            }

            // 6. Получаем аудиторию
            val roomDoc = roomsCollection.document(schedule.roomID).get().await()
            val room = roomDoc.toObject(Room::class.java)
                ?: throw Exception("Room not found")

            // 7. Определяем тип недели (четная или нечетная)
            val isEvenWeek = schedule.weekType == "Even"
            val isOddWeek = schedule.weekType == "Odd"

            // 8. Создаем объект ScheduleWithDetails
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
}