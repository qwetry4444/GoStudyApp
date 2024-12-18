package com.example.gostudyapp.core.data.database.mappers

import com.example.gostudyapp.core.data.database.entities.ScheduleSubgroupDto
import com.example.gostudyapp.core.domain.model.Schedule.ScheduleSubgroup

fun ScheduleSubgroupDto.toDomain(): ScheduleSubgroup {
    return ScheduleSubgroup(
        scheduleID = scheduleID,
        subgroupID = subgroupID
    )
}

fun ScheduleSubgroup.toDto(): ScheduleSubgroupDto {
    return ScheduleSubgroupDto(
        scheduleID = scheduleID,
        subgroupID = subgroupID
    )
}