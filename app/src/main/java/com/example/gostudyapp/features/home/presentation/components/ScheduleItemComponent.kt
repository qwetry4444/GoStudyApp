package com.example.gostudyapp.features.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gostudyapp.core.domain.model.Schedule.ScheduleWithDetails
import com.example.gostudyapp.core.domain.model.util.ClassNumberToTime

@Composable
fun ScheduleItemComponent(scheduleWithDetails: ScheduleWithDetails, modifier: Modifier = Modifier){
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF9C4AF5)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(14.dp)
                .weight(0.25f)
        ) {
            Column {
                Text(text = "${scheduleWithDetails.number} пара", color = Color.White, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = ClassNumberToTime(scheduleWithDetails.number), color = Color.White, fontSize = 14.sp)
            }
        }

        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(14.dp)
                .weight(0.55f)
        ) {
            Column {
                //Text(text = scheduleWithDetails.subject, color = Color.White, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(6.dp))
                //Text(text = scheduleWithDetails.teachers.first(), color = Color.White, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }

        Box(modifier = Modifier
            .padding(14.dp)
            .weight(0.18f)
        ) {
            //Text(text = scheduleWithDetails.room, color = Color.White, fontSize = 14.sp)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ScheduleItemComponentPreview(){
//    ScheduleItemComponent(
//        ScheduleWithDetails(
//        "Monday",
//        1,
//        "Математика",
//        listOf("Иван Иванович"),
//        listOf("609-22"),
//        "У901",
//        false,
//        true,
//        listOf("609-22a", "609-22б"))
//    )
//}