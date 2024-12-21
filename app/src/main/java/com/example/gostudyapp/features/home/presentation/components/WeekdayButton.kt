package com.example.gostudyapp.features.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gostudyapp.features.home.presentation.components.util.Weekday
import com.example.gostudyapp.features.home.presentation.components.util.WeekdayToString
import com.example.gostudyapp.features.home.presentation.components.util.WeekdayWithDate
import com.example.gostudyapp.ui.theme.WeekdayButtonGradientEnd
import com.example.gostudyapp.ui.theme.WeekdayButtonGradientStart

//@Composable
//fun WeekdayButton(weekday: Weekday, date: LocalDate, isActive: Boolean, modifier: Modifier) {
//    Box(contentAlignment = Center,
//        modifier = modifier
//        .clip(RoundedCornerShape(10.dp))
//        .background(if(isActive)  (Brush.verticalGradient(listOf(WeekdayButtonGradientStart, WeekdayButtonGradientEnd)))
//                    else Brush.linearGradient(listOf(Color.Transparent, Color.Transparent)))
//    ) {
//        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
//            Text(text = WeekdayToString(weekday), color = (if (isActive) Color.White else Color.Black))
//            Text(text = date.dayOfMonth.toString(), color = (if (isActive) Color.White else Color.Black))
//        }
//    }
//}

@Composable
fun WeekdayButton(weekdayWithDate: WeekdayWithDate, currentSelectedWeekday: Weekday, onClick: (Weekday) -> Unit) {
    val isActive = weekdayWithDate.weekday == currentSelectedWeekday
    Box(contentAlignment = Center,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(if(isActive)  (Brush.verticalGradient(listOf(WeekdayButtonGradientStart, WeekdayButtonGradientEnd)))
                        else Brush.linearGradient(listOf(Color.Transparent, Color.Transparent))
            )
            .clickable(enabled = !isActive, onClick = { onClick(weekdayWithDate.weekday) })
    ) {
        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = WeekdayToString(weekdayWithDate.weekday), color = (if (isActive) Color.White else Color.Black))
            Text(text = weekdayWithDate.date.dayOfMonth.toString(), color = (if (isActive) Color.White else Color.Black))
        }
    }
}
