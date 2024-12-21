package com.example.gostudyapp.features.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.gostudyapp.features.home.presentation.components.util.Weekday
import com.example.gostudyapp.features.home.presentation.components.util.WeekdayWithDate

@Composable
fun WeekdayButtonsList(weekdaysWithDate: List<WeekdayWithDate>, currentSelectedWeekday: Weekday, onWeekdayClick: (Weekday) -> Unit){
    LazyRow(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
        items(weekdaysWithDate) { weekdayWithDate ->
            WeekdayButton(weekdayWithDate = weekdayWithDate, currentSelectedWeekday = currentSelectedWeekday, onClick = onWeekdayClick)
        }
    }
}