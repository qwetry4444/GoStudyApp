package com.example.gostudyapp.features.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gostudyapp.R
import com.example.gostudyapp.core.domain.model.Schedule.Lesson
import com.example.gostudyapp.features.home.presentation.components.ClassTodayComponent
import com.example.gostudyapp.features.home.presentation.components.NoteComponent
import com.example.gostudyapp.features.home.presentation.components.ScheduleListComponent
import com.example.gostudyapp.features.home.presentation.components.WeekdayButtonsList
import com.example.gostudyapp.features.home.presentation.components.util.TodayClassSequence
import com.google.type.DateTime

@Composable
fun HomePage(
    homeViewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
){
    val homeState = homeViewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxHeight()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally) {

            Box(modifier = Modifier.padding(20.dp)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Row {
                            Text(text = stringResource(id = R.string.notes))
                            Image(
                                painter = painterResource(id = R.drawable.notes),
                                contentDescription = stringResource(id = R.string.notesDescriptionProfile)
                            )
                        }
                        Column {
                            NoteComponent(
                                title = stringResource(id = R.string.note1_title),
                                date = DateTime.getDefaultInstance()
                            )
                            NoteComponent(
                                title = stringResource(id = R.string.note2_title),
                                date = DateTime.getDefaultInstance()
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Column {
                        ClassTodayComponent(
                            lesson = Lesson(1, "3", number = 2, room = "У903"),
                            classSequence = TodayClassSequence.Now
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        ClassTodayComponent(
                            lesson = Lesson(
                                2,
                                "МатанализМатанализМатанализМатанализМатанализМатанализМатанализ ",
                                number = 3,
                                room = "А234"
                            ), classSequence = TodayClassSequence.Next
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
            Box() {
                Column {
                    Text(text = "Расписание")
                    Spacer(modifier = Modifier.height(11.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                        WeekdayButtonsList(homeState.value.weekdaysWithDate, homeState.value.selectedWeekday, {weekday -> homeViewModel.onWeekdayClicked(weekday)})
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box{
                ScheduleListComponent(scheduleWithDetailsList = homeState.value.currentScheduleList)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomePagePreview(){
    HomePage()
}