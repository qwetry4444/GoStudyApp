package com.example.gostudyapp.features.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.gostudyapp.core.domain.model.Schedule.ScheduleWithDetails
import com.example.gostudyapp.core.presentation.components.HeaderComponent
import com.example.gostudyapp.features.home.presentation.components.ClassTodayComponent
import com.example.gostudyapp.features.home.presentation.components.NoteComponent
import com.example.gostudyapp.features.home.presentation.components.ScheduleItemComponent
import com.example.gostudyapp.features.home.presentation.components.WeekdayButton
import com.example.gostudyapp.features.home.presentation.components.util.TodayClassSequence
import com.example.gostudyapp.features.home.presentation.components.util.Weekday
import com.google.type.DateTime
import java.time.LocalDate

@Composable
fun HomePage(
    homeViewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
){
    val homeState = homeViewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Box {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(scrollState)) {

            HeaderComponent()

            Box(modifier = Modifier.padding(20.dp)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(modifier = Modifier) {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = stringResource(id = R.string.contentDescriptionProfile),
                            modifier = Modifier.size(12.dp)
                        )
                        Text(text = stringResource(id = R.string.your_profile))
                    }

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
                        WeekdayButton(
                            weekday = Weekday.Monday,
                            date = homeViewModel.weekdays.getOrDefault(
                                Weekday.Monday,
                                LocalDate.now()
                            ),
                            isActive = homeState.value.selectedWeekday == Weekday.Monday,
                            modifier = Modifier.clickable {
                                homeViewModel.onWeekdayClicked(
                                    Weekday.Monday
                                )
                            }
                        )
                        WeekdayButton(
                            weekday = Weekday.Tuesday,
                            date = homeViewModel.weekdays.getOrDefault(
                                Weekday.Tuesday,
                                LocalDate.now()
                            ),
                            isActive = homeState.value.selectedWeekday == Weekday.Tuesday,
                            modifier = Modifier.clickable {
                                homeViewModel.onWeekdayClicked(
                                    Weekday.Tuesday
                                )
                            }
                        )
                        WeekdayButton(
                            weekday = Weekday.Wednesday,
                            date = homeViewModel.weekdays.getOrDefault(
                                Weekday.Wednesday,
                                LocalDate.now()
                            ),
                            isActive = homeState.value.selectedWeekday == Weekday.Wednesday,
                            modifier = Modifier.clickable {
                                homeViewModel.onWeekdayClicked(
                                    Weekday.Wednesday
                                )
                            }
                        )
                        WeekdayButton(
                            weekday = Weekday.Thursday,
                            date = homeViewModel.weekdays.getOrDefault(
                                Weekday.Thursday,
                                LocalDate.now()
                            ),
                            isActive = homeState.value.selectedWeekday == Weekday.Thursday,
                            modifier = Modifier.clickable {
                                homeViewModel.onWeekdayClicked(
                                    Weekday.Thursday
                                )
                            }
                        )
                        WeekdayButton(
                            weekday = Weekday.Friday,
                            date = homeViewModel.weekdays.getOrDefault(
                                Weekday.Friday,
                                LocalDate.now()
                            ),
                            isActive = homeState.value.selectedWeekday == Weekday.Friday,
                            modifier = Modifier.clickable {
                                homeViewModel.onWeekdayClicked(
                                    Weekday.Friday
                                )
                            }
                        )
                        WeekdayButton(
                            weekday = Weekday.Saturday,
                            date = homeViewModel.weekdays.getOrDefault(
                                Weekday.Saturday,
                                LocalDate.now()
                            ),
                            isActive = homeState.value.selectedWeekday == Weekday.Saturday,
                            modifier = Modifier.clickable {
                                homeViewModel.onWeekdayClicked(
                                    Weekday.Saturday
                                )
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            val scheduleWithDetails = ScheduleWithDetails(
                "Monday",
                1,
                "МатематикаМатематикаМатематика",
                listOf("Иван Иванович"),
                listOf("609-22"),
                "У901",
                false,
                true,
                listOf("609-22a", "609-22б")
            )
            Box {
                Column {
                    ScheduleItemComponent(scheduleWithDetails)
                    ScheduleItemComponent(scheduleWithDetails)
                    ScheduleItemComponent(scheduleWithDetails)
                }
            }


        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomePagePreview(){
    HomePage()
}