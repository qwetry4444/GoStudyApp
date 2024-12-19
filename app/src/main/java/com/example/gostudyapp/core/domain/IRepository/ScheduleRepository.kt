package com.example.gostudyapp.core.domain.IRepository

import com.example.gostudyapp.core.data.database.entities.ScheduleDto
import com.example.gostudyapp.core.domain.model.Schedule.Schedule
import com.example.gostudyapp.core.domain.model.Schedule.ScheduleWithDetails

interface ScheduleRepository {
    suspend fun getScheduleById(scheduleId: String): Schedule?
    suspend fun getAllSchedules(): List<Schedule>
    suspend fun addSchedule(schedule: Schedule)

    suspend fun getScheduleForSubgroup(subgroupID: String): List<ScheduleDto>
    suspend fun getScheduleWithDetails(scheduleID: String): ScheduleWithDetails
}