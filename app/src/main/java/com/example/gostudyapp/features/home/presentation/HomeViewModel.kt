package com.example.gostudyapp.features.home.presentation

import com.example.gostudyapp.core.presentation.BaseViewModel
import com.example.gostudyapp.features.home.presentation.components.util.Weekday
import com.example.gostudyapp.features.home.presentation.components.util.getCurrentWeekDates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.asStateFlow()
    var weekdays: Map<Weekday, LocalDate> = getCurrentWeekDates();
    var localDate: LocalDate = LocalDate.now()

    fun onWeekdayClicked(weekday: Weekday) {
        uiState.value.selectedWeekday = weekday
        _uiState.update { currentState ->
            currentState.copy(selectedWeekday = weekday)
        }
    }
}