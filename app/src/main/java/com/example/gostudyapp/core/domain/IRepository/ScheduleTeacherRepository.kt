package com.example.gostudyapp.core.domain.IRepository

import com.example.gostudyapp.core.domain.model.Schedule.ScheduleTeacher

interface ScheduleTeacherRepository {
    suspend fun getScheduleTeacherById(scheduleTeacherId: String): ScheduleTeacher?
    suspend fun getAllScheduleTeachers(): List<ScheduleTeacher>
    suspend fun addScheduleTeacher(scheduleTeacher: ScheduleTeacher)
}