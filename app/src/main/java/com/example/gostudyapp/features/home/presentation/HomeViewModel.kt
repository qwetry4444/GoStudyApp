package com.example.gostudyapp.features.home.presentation

import androidx.lifecycle.viewModelScope
import com.example.gostudyapp.core.data.auth.impl.AccountService
import com.example.gostudyapp.core.domain.usecases.ScheduleUseCase.GetScheduleWithDetailsForSubgroupUseCase
import com.example.gostudyapp.core.presentation.BaseViewModel
import com.example.gostudyapp.features.home.presentation.components.util.Weekday
import com.example.gostudyapp.features.home.presentation.components.util.getCurrentWeekday
import com.example.gostudyapp.features.home.presentation.components.util.getCurrentWeekdaysWithDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getScheduleWithDetailsForSubgroupUseCase: GetScheduleWithDetailsForSubgroupUseCase,
    private val accountService: AccountService
) : BaseViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.asStateFlow()

//    var localDate: LocalDate = LocalDate.now()
//    var currentWeekdaysWithDate: List<WeekdayWithDate> = listOf()
//    val currentUser = accountService.currentUser()
//    var currentScheduleList: List<ScheduleWithDetails> = listOf()

    init {
        loadUserData()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            try {
                _uiState.update { currentState -> currentState.copy(selectedWeekday = getCurrentWeekday()) }
                _uiState.update { currentState -> currentState.copy(weekdaysWithDate = getCurrentWeekdaysWithDate()) }

                val userData = accountService.getUserData()
                val subgroupId = userData?.get("subgroup_id")
                _uiState.update { currentState -> currentState.copy(userGroupNumber = subgroupId.toString()) }
                _uiState.update { currentState -> currentState.copy(fullScheduleList =
                getScheduleWithDetailsForSubgroupUseCase.invoke(_uiState.value.userGroupNumber))
                }
            } catch (_: Exception) { }
        }
    }

    fun onWeekdayClicked(weekday: Weekday) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedWeekday = weekday,
                currentScheduleList = _uiState.value.fullScheduleList.filter
                    { it.weekday == weekday.value.toString() }.sortedBy { it.number }
            )
        }

//
//        viewModelScope.launch(Dispatchers.IO) {
//
//            //addRoomUseCase.invoke(Room("903", "У"))
//            //addGroupUseCase.invoke(Group(groupNumber = "609-22", "Программная инженерия"))
//            //addSubjectUseCase.invoke(Subject("Организация МПС"))
//            //addTeacherUseCase.invoke(Teacher("Андрей", "Запевалов", "Автоматики и компьютерных систем"))
//            //addSubgroupUseCase.invoke(Subgroup("GM6z3GIgr9BYavQbZyuL", "а"))
//            //addSubgroupUseCase.invoke(Subgroup("GM6z3GIgr9BYavQbZyuL", "б"))
//            //addScheduleUseCase.invoke(Schedule("3cRxwDNyxBmMTYlAieFl", "XdYYU992SKHAyhAW5adc", "Monday", 2, "Both"))
//            //addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("RtVj7Q7yP9eafeW2cNvn", "HT7pYzq5ll5K4AXrRqCJ"))
//            //addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("RtVj7Q7yP9eafeW2cNvn", "Wm2cgI6CongYmJ77x5us"))
//            //addScheduleTeacherUseCase.invoke(ScheduleTeacher("RtVj7Q7yP9eafeW2cNvn", "H3TMAvxSqa8Z0KYThxIM"))
//            //val x = getScheduleWithDetailsUseCase.invoke("RtVj7Q7yP9eafeW2cNvn")
//        }
    }

}