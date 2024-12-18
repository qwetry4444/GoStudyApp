package com.example.gostudyapp.core.domain.IRepository

import com.example.gostudyapp.core.domain.model.Schedule.Subgroup

interface SubgroupRepository {
    suspend fun getSubgroupById(subgroupId: String): Subgroup?
    suspend fun getAllSubgroups(): List<Subgroup>
    suspend fun addSubgroup(subgroup: Subgroup)
}