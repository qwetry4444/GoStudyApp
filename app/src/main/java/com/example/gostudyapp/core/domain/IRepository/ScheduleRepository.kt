package com.example.gostudyapp.core.domain.IRepository

import com.example.gostudyapp.core.domain.model.Schedule.Schedule

interface ScheduleRepository {
    suspend fun getScheduleById(scheduleId: String): Schedule?
    suspend fun getAllSchedules(): List<Schedule>
    suspend fun addSchedule(schedule: Schedule)
}