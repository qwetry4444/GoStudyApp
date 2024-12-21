package com.example.gostudyapp.core.domain.IRepository

import com.example.gostudyapp.core.domain.model.Schedule.Group

interface GroupRepository {
    suspend fun getGroupById(groupId: String) : Group?
    suspend fun getAllGroups() : List<Group>
    suspend fun addGroup(group: Group)
    suspend fun getGroupIdByNumber(groupNumber: String): String
}