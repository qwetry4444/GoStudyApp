package com.example.gostudyapp.features.profile.Presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gostudyapp.core.presentation.navigation.Route
import com.example.gostudyapp.ui.theme.QuitButtonBackground

@Composable
fun ProfilePage(
    navigateOnSignOut: (Route) -> Unit,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val profileState = profileViewModel.uiState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Ваш профиль",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Text(
                    text = "Логин: ${profileState.userLogin}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Группа: ${profileState.groupNumber}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }


        Text(
            text = "Изменить номер группы",
            style = MaterialTheme.typography.titleMedium,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Box(modifier = Modifier.weight(1f)){
                OutlinedTextField(
                    value = profileState.currentInputGroupNumber,
                    onValueChange = { profileViewModel.onGroupNumberChanged(it) },
                    modifier = Modifier
                            .padding(end = 8.dp),
                    label = { Text("Номер группы") },
                    shape = RoundedCornerShape(12.dp),
                    isError = profileState.isInputGroupNumberWrong,
                    supportingText = {
                        profileState.errorMessage?.let {
                            Text(text = it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                )
            }

            Box(modifier = Modifier.weight(0.4f)) {
                OutlinedTextField(
                    value = profileState.selectedSubgroupNumber ?: "а/б",
                    onValueChange = { },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { }
                        .padding(end = 8.dp, top = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    trailingIcon = {
                        val rotation by animateFloatAsState(
                            targetValue = if (profileState.isMenuExpanded) 0f else 180f,
                            label = "RotateDropdownIcon"
                        )
                        IconButton(onClick = { profileViewModel.toggleMenu() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Показать меню",
                                modifier = Modifier.rotate(rotation),
                                tint = if (profileState.isMenuExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                )

                DropdownMenu(
                    expanded = profileState.isMenuExpanded,
                    onDismissRequest = { profileViewModel.toggleMenu() },
                    modifier = Modifier
                ) {
                    listOf("а", "б").forEach { subgroup ->
                        DropdownMenuItem(
                            text = { Text(text = subgroup) },
                            onClick = {
                                profileViewModel.onItemSelected(subgroup)
                            }
                        )
                    }
                }
            }
        }

        Button(
            onClick = { profileViewModel.submitGroupChange() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .height(40.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Применить изменения")
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Button(
                onClick = {
                    profileViewModel.signOut()
                    navigateOnSignOut(Route.SignIn)
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .height(48.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = QuitButtonBackground,
                    contentColor = QuitButtonBackground,
                )
            ) {
                Text(text = "Выйти из аккаунта", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Preview
@Composable
fun ProfilePagePreview(){
    ProfilePage(navigateOnSignOut = {})
}


