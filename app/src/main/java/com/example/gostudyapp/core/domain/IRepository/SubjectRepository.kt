package com.example.gostudyapp.core.domain.IRepository

import com.example.gostudyapp.core.domain.model.Schedule.Subject

interface SubjectRepository {
    suspend fun getSubjectById(subjectId: String): Subject?
    suspend fun getAllSubjects(): List<Subject>
    suspend fun addSubject(subject: Subject)
}