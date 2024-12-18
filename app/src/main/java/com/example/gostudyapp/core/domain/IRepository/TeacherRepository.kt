package com.example.gostudyapp.core.domain.IRepository

import com.example.gostudyapp.core.domain.model.Schedule.Teacher

interface TeacherRepository {
    suspend fun getTeacherById(teacherId: String): Teacher?
    suspend fun getAllTeachers(): List<Teacher>
    suspend fun addTeacher(teacher: Teacher)
}