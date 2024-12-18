package com.example.gostudyapp.features.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gostudyapp.R
import com.example.gostudyapp.core.domain.model.Schedule.Lesson
import com.example.gostudyapp.core.domain.model.util.ClassNumberToTime
import com.example.gostudyapp.features.home.presentation.components.util.TodayClassSequence
import com.example.gostudyapp.ui.theme.TodayClassGradientLeft

@Composable
fun ClassTodayComponent(lesson: Lesson, classSequence: TodayClassSequence){
    Column() {
        Row {
            Text(
                text = stringResource(
                    id = R.string.todayClassNumber,
                    stringResource(id = (if (classSequence == TodayClassSequence.Now) R.string.now else R.string.next)),
                    lesson.number
                )
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(TodayClassGradientLeft)) {
            Box(modifier = Modifier
                .padding(14.dp)
                .weight(0.25f)
                ) {
                Text(text = ClassNumberToTime(lesson.number), color = Color.White, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.width(1.dp))
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(14.dp)
                    .weight(0.55f)
            ) {
                Text(text = lesson.subject, color = Color.White, maxLines = 2, overflow = TextOverflow.Ellipsis)
            }

            Box(modifier = Modifier.background(color = Color.White)){
                Text(text = "123", modifier = Modifier.matchParentSize())
            }

            //Spacer(modifier = Modifier.width(1.dp))

            Box(modifier = Modifier
                .padding(14.dp)
                .weight(0.18f)
            ) {
                Text(text = lesson.room, color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClassTodayPreview(){
    Column() {
        ClassTodayComponent(lesson = Lesson(1, "МатанализМатанализМатанализМатанализ", number = 2, room = "У903"), classSequence = TodayClassSequence.Now)
        Spacer(modifier = Modifier.height(20.dp))
        ClassTodayComponent(lesson = Lesson(1, "1", number = 2, room = "У903"), classSequence = TodayClassSequence.Now)
    }
}