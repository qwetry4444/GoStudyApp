package com.example.gostudyapp.features.home.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gostudyapp.core.domain.model.Schedule.ScheduleWithDetails


@Composable
fun ScheduleListComponent(scheduleWithDetailsList: List<ScheduleWithDetails>){
    LazyColumn {
        items(scheduleWithDetailsList) { _scheduleWithDetails: ScheduleWithDetails ->
            ScheduleItemComponent(scheduleWithDetails = _scheduleWithDetails)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}