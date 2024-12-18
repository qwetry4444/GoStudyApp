package com.example.gostudyapp.core.data.database.repositories

import com.example.gostudyapp.core.data.database.entities.SubjectDto
import com.example.gostudyapp.core.data.database.mappers.toDomain
import com.example.gostudyapp.core.data.database.mappers.toDto
import com.example.gostudyapp.core.domain.IRepository.SubjectRepository
import com.example.gostudyapp.core.domain.model.Schedule.Subject
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubjectRepositoryImpl @Inject constructor(private val firestore: FirebaseFirestore) :
    SubjectRepository {

    override suspend fun getSubjectById(subjectId: String): Subject? {
        return try {
            val document = firestore.collection("subjects")
                .document(subjectId)
                .get()
                .await()

            document.toObject(SubjectDto::class.java)?.toDomain()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getAllSubjects(): List<Subject> {
        return try {
            firestore.collection("subjects")
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(SubjectDto::class.java)?.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun addSubject(subject: Subject) {
        try {
            firestore.collection("subjects")
                .add(subject.toDto())
                .await()
        } catch (_: Exception) { }
    }
}