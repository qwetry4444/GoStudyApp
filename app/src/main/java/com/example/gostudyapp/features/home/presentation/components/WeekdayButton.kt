package com.example.gostudyapp.features.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.gostudyapp.R
import com.example.gostudyapp.features.home.presentation.components.util.Weekday
import com.example.gostudyapp.features.home.presentation.components.util.WeekdayToString
import java.time.LocalDate

@Composable
fun WeekdayButton(weekday: Weekday, date: LocalDate, isActive: Boolean, modifier: Modifier) {
    Box(modifier = modifier
        .padding(4.dp)
        .background(if(isActive)  (Brush.horizontalGradient(listOf(colorResource(id = R.color.headerGradientStart),colorResource(id = R.color.headerGradientEnd))))
        else Brush.linearGradient()
        )
    ){
        Column {
            Text(text = WeekdayToString(weekday))
            Text(text = date.dayOfMonth.toString())
        }
    }
}