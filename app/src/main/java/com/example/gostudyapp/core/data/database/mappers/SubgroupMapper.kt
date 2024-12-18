package com.example.gostudyapp.core.data.database.mappers

import com.example.gostudyapp.core.data.database.entities.SubgroupDto
import com.example.gostudyapp.core.domain.model.Schedule.Subgroup

fun SubgroupDto.toDomain(): Subgroup {
    return Subgroup(
        groupID = groupID,
        subgroupName = subgroupName
    )
}

fun Subgroup.toDto(): SubgroupDto {
    return SubgroupDto(
        groupID = groupID,
        subgroupName = subgroupName
    )
}