package com.example.gostudyapp.core.data.database.mappers

import android.annotation.SuppressLint
import android.database.Cursor
import com.example.gostudyapp.core.domain.model.Schedule.ScheduleWithDetails

@SuppressLint("Range")
fun mapToScheduleWithDetails(cursor: Cursor): ScheduleWithDetails {
    val teachersStr = cursor.getString(cursor.getColumnIndex("teachers"))
    val groupsStr = cursor.getString(cursor.getColumnIndex("groups"))
    val subgroupsStr = cursor.getString(cursor.getColumnIndex("subgroups"))

    val teachers = teachersStr?.split(",")?.map { it.trim() } ?: emptyList()
    val groups = groupsStr?.split(",")?.map { it.trim() } ?: emptyList()
    val subgroups = subgroupsStr?.split(",")?.map { it.trim() } ?: emptyList()

    return ScheduleWithDetails(
        weekday = cursor.getString(cursor.getColumnIndex("weekday")),
        number = cursor.getInt(cursor.getColumnIndex("number")),
        subject = cursor.getString(cursor.getColumnIndex("subject")),
        teachers = teachers,
        groups = groups,
        room = cursor.getString(cursor.getColumnIndex("room")),
        isEvenWeek = cursor.getInt(cursor.getColumnIndex("isEvenWeek")) == 1,
        isOddWeek = cursor.getInt(cursor.getColumnIndex("isOddWeek")) == 1,
        subgroups = subgroups
    )
}