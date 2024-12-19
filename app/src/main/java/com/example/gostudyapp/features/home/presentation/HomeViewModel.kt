package com.example.gostudyapp.features.home.presentation

import androidx.lifecycle.viewModelScope
import com.example.gostudyapp.core.domain.usecases.GroupUseCase.AddGroupUseCase
import com.example.gostudyapp.core.domain.usecases.RoomUseCase.AddRoomUseCase
import com.example.gostudyapp.core.domain.usecases.ScheduleSubgroupUseCase.AddScheduleSubgroupUseCase
import com.example.gostudyapp.core.domain.usecases.ScheduleTeacherUseCase.AddScheduleTeacherUseCase
import com.example.gostudyapp.core.domain.usecases.ScheduleUseCase.AddScheduleUseCase
import com.example.gostudyapp.core.domain.usecases.ScheduleUseCase.GetScheduleForSubgroupUseCase
import com.example.gostudyapp.core.domain.usecases.ScheduleUseCase.GetScheduleWithDetailsUseCase
import com.example.gostudyapp.core.domain.usecases.SubgroupUseCase.AddSubgroupUseCase
import com.example.gostudyapp.core.domain.usecases.SubjectUseCase.AddSubjectUseCase
import com.example.gostudyapp.core.domain.usecases.TeacherUseCase.AddTeacherUseCase
import com.example.gostudyapp.core.presentation.BaseViewModel
import com.example.gostudyapp.features.home.presentation.components.util.Weekday
import com.example.gostudyapp.features.home.presentation.components.util.getCurrentWeekDates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val addRoomUseCase: AddRoomUseCase,
    private val addGroupUseCase: AddGroupUseCase,
    private val addScheduleUseCase: AddScheduleUseCase,
    private val addSubgroupUseCase: AddSubgroupUseCase,
    private val addSubjectUseCase: AddSubjectUseCase,
    private val addTeacherUseCase: AddTeacherUseCase,
    private val addScheduleSubgroupUseCase: AddScheduleSubgroupUseCase,
    private val addScheduleTeacherUseCase: AddScheduleTeacherUseCase,
    private val getScheduleForSubgroupUseCase: GetScheduleForSubgroupUseCase,
    private val getScheduleWithDetailsUseCase: GetScheduleWithDetailsUseCase,

) : BaseViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.asStateFlow()
    var weekdays: Map<Weekday, LocalDate> = getCurrentWeekDates();
    var localDate: LocalDate = LocalDate.now()

    fun onWeekdayClicked(weekday: Weekday) {
        _uiState.update { currentState ->
            currentState.copy(selectedWeekday = weekday)
        }
        viewModelScope.launch(Dispatchers.IO) {
            //addRoomUseCase.invoke(Room("903", "У"))
            //addGroupUseCase.invoke(Group(groupNumber = "609-22", "Программная инженерия"))
            //addSubjectUseCase.invoke(Subject("Организация МПС"))
            //addTeacherUseCase.invoke(Teacher("Андрей", "Запевалов", "Автоматики и компьютерных систем"))
            //addSubgroupUseCase.invoke(Subgroup("GM6z3GIgr9BYavQbZyuL", "а"))
            //addSubgroupUseCase.invoke(Subgroup("GM6z3GIgr9BYavQbZyuL", "б"))
            //addScheduleUseCase.invoke(Schedule("3cRxwDNyxBmMTYlAieFl", "XdYYU992SKHAyhAW5adc", "Monday", 2, "Both"))
            //addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("RtVj7Q7yP9eafeW2cNvn", "HT7pYzq5ll5K4AXrRqCJ"))
            //addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("RtVj7Q7yP9eafeW2cNvn", "Wm2cgI6CongYmJ77x5us"))
            //addScheduleTeacherUseCase.invoke(ScheduleTeacher("RtVj7Q7yP9eafeW2cNvn", "H3TMAvxSqa8Z0KYThxIM"))
            val x = getScheduleWithDetailsUseCase.invoke("RtVj7Q7yP9eafeW2cNvn")
        }
    }

}