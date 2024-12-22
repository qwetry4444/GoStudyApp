package com.example.gostudyapp.features.home.presentation

import androidx.lifecycle.viewModelScope
import com.example.gostudyapp.core.data.auth.impl.AccountService
import com.example.gostudyapp.core.domain.usecases.ScheduleSubgroupUseCase.AddScheduleSubgroupUseCase
import com.example.gostudyapp.core.domain.usecases.ScheduleTeacherUseCase.AddScheduleTeacherUseCase
import com.example.gostudyapp.core.domain.usecases.ScheduleUseCase.AddScheduleUseCase
import com.example.gostudyapp.core.domain.usecases.ScheduleUseCase.GetScheduleWithDetailsForSubgroupUseCase
import com.example.gostudyapp.core.domain.usecases.TeacherUseCase.AddTeacherUseCase
import com.example.gostudyapp.core.presentation.BaseViewModel
import com.example.gostudyapp.features.home.presentation.components.util.Weekday
import com.example.gostudyapp.features.home.presentation.components.util.getCurrentWeekday
import com.example.gostudyapp.features.home.presentation.components.util.getCurrentWeekdaysWithDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getScheduleWithDetailsForSubgroupUseCase: GetScheduleWithDetailsForSubgroupUseCase,
    private val accountService: AccountService,

    private val addScheduleUseCase: AddScheduleUseCase,
    private val addScheduleSubgroupUseCase: AddScheduleSubgroupUseCase,
    private val addScheduleTeacherUseCase: AddScheduleTeacherUseCase,
    private val addTeacherUseCase: AddTeacherUseCase
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


        viewModelScope.launch(Dispatchers.IO) {
            //addScheduleUseCase.invoke(Schedule("wiZgFFmTaagz29FJe0Ov", "BGmpNqH4DCHcJ3s91s7r", 3, 4, "Both"))
            //addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("RtVj7Q7yP9eafeW2cNvn", "HT7pYzq5ll5K4AXrRqCJ"))
            //addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("RtVj7Q7yP9eafeW2cNvn", "Wm2cgI6CongYmJ77x5us"))
            //addScheduleTeacherUseCase.invoke(ScheduleTeacher("RtVj7Q7yP9eafeW2cNvn", "H3TMAvxSqa8Z0KYThxIM"))
//            //val x = getScheduleWithDetailsUseCase.invoke("RtVj7Q7yP9eafeW2cNvn")

//            /*2*/addScheduleUseCase.invoke(Schedule("nvdMXIeWnNrKEyyjqdby", "ka1tThIQ3IUpuu3GcPTg", 3, 5, "Both"))
//
//            /*3*/addScheduleUseCase.invoke(Schedule("gxkb66okpMsFascjzbvq", "syGTlrVeesNyHIfOPLxi", 3, 5, "Both"))
//
//            /*4*/addScheduleUseCase.invoke(Schedule("nvdMXIeWnNrKEyyjqdby", "ka1tThIQ3IUpuu3GcPTg", 3, 6, "Both"))
//
//            /*5*/addScheduleUseCase.invoke(Schedule("dTGc4ebt5AFFBHvPxGkp", "BGmpNqH4DCHcJ3s91s7r", 4, 4, "Both"))
//
//            /*6*/addScheduleUseCase.invoke(Schedule("nvdMXIeWnNrKEyyjqdby", "BGmpNqH4DCHcJ3s91s7r", 4, 5, "Both"))
//
//            /*7*/addScheduleUseCase.invoke(Schedule("KkFeI6tTyYvx3vxayFVB", "7tj53snKgbhzpJmqv5QI", 4, 7, "Odd"))
//
//            /*8*/addScheduleUseCase.invoke(Schedule("ljK10Wua6DGE78CtlLLl", "zpFp9InPnNDUh945H4YG", 5, 1, "Both"))
//
//            /*9*/addScheduleUseCase.invoke(Schedule("3cRxwDNyxBmMTYlAieFl", "xt2TaK2AcFcMc7m66YOY", 5, 4, "Both"))
//
//            /*10*/addScheduleUseCase.invoke(Schedule("dTGc4ebt5AFFBHvPxGkp", "XMV3jwqZuayHbkWkeQAA", 5, 4, "Both"))
//
//            /*11*/addScheduleUseCase.invoke(Schedule("3cRxwDNyxBmMTYlAieFl", "xt2TaK2AcFcMc7m66YOY", 5, 5, "Both"))
//
//            /*12*/addScheduleUseCase.invoke(Schedule("Yf9TUhzl0n1a2cnF1mNe", "ka1tThIQ3IUpuu3GcPTg", 5, 6, "Both"))
//
//            /*13*/addScheduleUseCase.invoke(Schedule("wiZgFFmTaagz29FJe0Ov", "chDMAWnUi9rJMjFas3Qb", 6, 1, "Even"))
//
//            /*14*/addScheduleUseCase.invoke(Schedule("wiZgFFmTaagz29FJe0Ov", "chDMAWnUi9rJMjFas3Qb", 6, 2, "Odd"))
//
//            /*15*/addScheduleUseCase.invoke(Schedule("Yf9TUhzl0n1a2cnF1mNe", "BGmpNqH4DCHcJ3s91s7r", 6, 3, "Odd"))

//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("1V0fdV6R1LEBuZUoAJvL", "1t7Ldd2dqeq7zonme2cV"))
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("1V0fdV6R1LEBuZUoAJvL", "9ms5h27g39tsMrzak7Yp"))
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("1V0fdV6R1LEBuZUoAJvL", "HT7pYzq5ll5K4AXrRqCJ"))
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("1V0fdV6R1LEBuZUoAJvL", "Wm2cgI6CongYmJ77x5us"))
//            addScheduleTeacherUseCase.invoke(ScheduleTeacher("1V0fdV6R1LEBuZUoAJvL", "wUnln90u1InGfprb4qB2"))
//
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("3MhTTbSFsQrzcoQ4SwA8", "HT7pYzq5ll5K4AXrRqCJ"))
//            addScheduleTeacherUseCase.invoke(ScheduleTeacher("3MhTTbSFsQrzcoQ4SwA8", "8wWPLeNpJPgIGOjjqb04"))
//
//
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("MqqJmb9eqafZioX5K1fp", "HT7pYzq5ll5K4AXrRqCJ"))
//            addScheduleTeacherUseCase.invoke(ScheduleTeacher("MqqJmb9eqafZioX5K1fp", "ykjVy2Gno0GEQ0mQ1jCU"))
//
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("NyYgCEp4WDoIG2woNT6X", "1t7Ldd2dqeq7zonme2cV"))
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("NyYgCEp4WDoIG2woNT6X", "9ms5h27g39tsMrzak7Yp"))
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("NyYgCEp4WDoIG2woNT6X", "HT7pYzq5ll5K4AXrRqCJ"))
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("NyYgCEp4WDoIG2woNT6X", "Wm2cgI6CongYmJ77x5us"))
//            addScheduleTeacherUseCase.invoke(ScheduleTeacher("NyYgCEp4WDoIG2woNT6X", "HFawl4gLHMh7mSYZGt0i"))
//
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("OYQ0Lr7hU7R3rsWg08hW", "HT7pYzq5ll5K4AXrRqCJ"))
//            addScheduleTeacherUseCase.invoke(ScheduleTeacher("OYQ0Lr7hU7R3rsWg08hW", "IqEXZ9vJO8ANNwz2rPFK"))
//
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("VHaGOS04J6Vqr0Pc3IgM", "HT7pYzq5ll5K4AXrRqCJ"))
//            addScheduleTeacherUseCase.invoke(ScheduleTeacher("VHaGOS04J6Vqr0Pc3IgM", "8wWPLeNpJPgIGOjjqb04"))
//
//
     //   /*2*/addScheduleUseCase.invoke(Schedule("dTGc4ebt5AFFBHvPxGkp", "BGmpNqH4DCHcJ3s91s7r", 3, 4, "Both"))

//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("tMUoY0A2mYpvQ93xXJ0F", "1t7Ldd2dqeq7zonme2cV"))
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("tMUoY0A2mYpvQ93xXJ0F", "9ms5h27g39tsMrzak7Yp"))
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("tMUoY0A2mYpvQ93xXJ0F", "HT7pYzq5ll5K4AXrRqCJ"))
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("tMUoY0A2mYpvQ93xXJ0F", "Wm2cgI6CongYmJ77x5us"))
//            addScheduleTeacherUseCase.invoke(ScheduleTeacher("tMUoY0A2mYpvQ93xXJ0F", "Qdfz9E7aeNcoX7Vjn4Cz"))
//
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("ZKW87iPoXtw3gwp7VNW1", "HT7pYzq5ll5K4AXrRqCJ"))
//            addScheduleTeacherUseCase.invoke(ScheduleTeacher("ZKW87iPoXtw3gwp7VNW1", "Wo5Z5Cqrxoy8oBtcChZL"))
//
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("cYTfoUf6hAsPh4cYbjLS", "HT7pYzq5ll5K4AXrRqCJ"))
//            addScheduleTeacherUseCase.invoke(ScheduleTeacher("cYTfoUf6hAsPh4cYbjLS", "4BADY22KRmBRfjclfp70"))
//
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("fgqZy0kseyXDStiz1QCm", "HT7pYzq5ll5K4AXrRqCJ"))
//            addScheduleTeacherUseCase.invoke(ScheduleTeacher("fgqZy0kseyXDStiz1QCm", "9chMAb5GQNi3bjrAYrbh"))
//
//
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("ljM00V8aERZ9yciaKZId", "Wm2cgI6CongYmJ77x5us"))
//            addScheduleTeacherUseCase.invoke(ScheduleTeacher("ljM00V8aERZ9yciaKZId", "IqEXZ9vJO8ANNwz2rPFK"))
//
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("r0yKxNyCAQLju7rjCVTr", "1t7Ldd2dqeq7zonme2cV"))
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("r0yKxNyCAQLju7rjCVTr", "9ms5h27g39tsMrzak7Yp"))
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("r0yKxNyCAQLju7rjCVTr", "HT7pYzq5ll5K4AXrRqCJ"))
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("r0yKxNyCAQLju7rjCVTr", "Wm2cgI6CongYmJ77x5us"))
//            addScheduleTeacherUseCase.invoke(ScheduleTeacher("r0yKxNyCAQLju7rjCVTr", "ykjVy2Gno0GEQ0mQ1jCU"))
//
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("yS3DSa48EguG0KYxsFTU", "Wm2cgI6CongYmJ77x5us"))
//            addScheduleTeacherUseCase.invoke(ScheduleTeacher("yS3DSa48EguG0KYxsFTU", "Wo5Z5Cqrxoy8oBtcChZL"))
//
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("ydEFjHbYGaqg5TDCEx6n", "1t7Ldd2dqeq7zonme2cV"))
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("ydEFjHbYGaqg5TDCEx6n", "9ms5h27g39tsMrzak7Yp"))
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("ydEFjHbYGaqg5TDCEx6n", "HT7pYzq5ll5K4AXrRqCJ"))
//            addScheduleSubgroupUseCase.invoke(ScheduleSubgroup("ydEFjHbYGaqg5TDCEx6n", "Wm2cgI6CongYmJ77x5us"))
//            addScheduleTeacherUseCase.invoke(ScheduleTeacher("ydEFjHbYGaqg5TDCEx6n", "ykjVy2Gno0GEQ0mQ1jCU"))
        }
    }
}