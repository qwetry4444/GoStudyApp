package com.example.gostudyapp.features.home.presentation

import androidx.lifecycle.viewModelScope
import com.example.gostudyapp.core.domain.model.Schedule.Group
import com.example.gostudyapp.core.domain.usecases.GroupUseCase.AddGroupUseCase
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
    private val addGroupUseCase: AddGroupUseCase) : BaseViewModel() {
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
            val x = addGroupUseCase.invoke(Group(groupNumber = "609-22", "Программная инженерия"))
        }
    }

}