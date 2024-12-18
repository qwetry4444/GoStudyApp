package com.example.gostudyapp.core.domain.IRepository

import com.example.gostudyapp.core.domain.model.Schedule.ScheduleSubgroup

interface ScheduleSubgroupRepository {
    suspend fun getScheduleSubgroupById(scheduleSubgroupId: String): ScheduleSubgroup?
    suspend fun getAllScheduleSubgroups(): List<ScheduleSubgroup>
    suspend fun addScheduleSubgroup(scheduleSubgroup: ScheduleSubgroup)
}