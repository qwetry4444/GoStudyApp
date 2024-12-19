package com.example.gostudyapp.features.profile.Presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProfilePage(
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val profileState = profileViewModel.uiState.collectAsState()

    Column {
        Row {
            Text(text = "Логин: ")
            Text(text = profileState.value.userLogin)
        }
        Row {
            Text(text = "Группа: ")
            Text(text = profileState.value.groupNumber)
        }

        Button(onClick = { profileViewModel.signOut() }) {
            Text(text = "Выйти")
        }
    }
}



