package com.example.gostudyapp.core.data.database.repositories

import com.example.gostudyapp.core.data.database.entities.TeacherDto
import com.example.gostudyapp.core.data.database.mappers.toDomain
import com.example.gostudyapp.core.data.database.mappers.toDto
import com.example.gostudyapp.core.domain.IRepository.TeacherRepository
import com.example.gostudyapp.core.domain.model.Schedule.Teacher
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeacherRepositoryImpl @Inject constructor(private val firestore: FirebaseFirestore) :
    TeacherRepository {

    override suspend fun getTeacherById(teacherId: String): Teacher? {
        return try {
            val document = firestore.collection("teachers")
                .document(teacherId)
                .get()
                .await()

            document.toObject(TeacherDto::class.java)?.toDomain()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getAllTeachers(): List<Teacher> {
        return try {
            firestore.collection("teachers")
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(TeacherDto::class.java)?.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun addTeacher(teacher: Teacher) {
        try {
            firestore.collection("teachers")
                .add(teacher.toDto())
                .await()
        } catch (_: Exception) { }
    }
}