package com.example.gostudyapp.features.home.presentation

import com.example.gostudyapp.features.home.presentation.components.util.Weekday

data class HomeState(
    var selectedWeekday: Weekday = Weekday.Monday
)
