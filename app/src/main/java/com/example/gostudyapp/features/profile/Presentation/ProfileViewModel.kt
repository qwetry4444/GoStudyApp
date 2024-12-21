package com.example.gostudyapp.features.profile.Presentation

import androidx.lifecycle.viewModelScope
import com.example.gostudyapp.core.data.auth.impl.AccountService
import com.example.gostudyapp.core.domain.model.Schedule.Group
import com.example.gostudyapp.core.domain.usecases.GroupUseCase.GetAllGroupsUseCase
import com.example.gostudyapp.core.domain.usecases.SubgroupUseCase.GetGroupNumberAndSubgroupNameUseCase
import com.example.gostudyapp.core.domain.usecases.SubgroupUseCase.GetSubgroupIdByGroupNumberAndSubgroupNameUseCase
import com.example.gostudyapp.core.domain.usecases.UserUseCase.UpdateUserGroupIDUseCase
import com.example.gostudyapp.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val accountService: AccountService,
    private val getAllGroupsUseCase: GetAllGroupsUseCase,
    private val updateUserGroupIDUseCase: UpdateUserGroupIDUseCase,
    private val getSubgroupIdByGroupNumberAndSubgroupNameUseCase: GetSubgroupIdByGroupNumberAndSubgroupNameUseCase,
    private val getGroupNumberAndSubgroupNameUseCase: GetGroupNumberAndSubgroupNameUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(ProfileState())
    val uiState = _uiState.asStateFlow()
    var allGroups: List<Group> = listOf()
    lateinit var uid: String

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                uid = accountService.currentUser()!!.uid
                allGroups = getAllGroupsUseCase.invoke()
                val userData = accountService.getUserData()
                val email = accountService.currentUser()?.email  ?: "Не указано"
                val subgroupId = userData?.get("subgroup_id").toString()
                val groupNumber = getGroupNumberAndSubgroupNameUseCase.invoke(subgroupId)
                _uiState.value = ProfileState(userLogin = email, groupNumber = groupNumber.toString())
            } catch (e: Exception) {
                _uiState.value = ProfileState()
            }
        }
    }

    fun isCurrentGroupNumberCorrect(): Boolean {
        return (allGroups.map { it.groupNumber }).contains(_uiState.value.currentInputGroupNumber)
    }

    fun onGroupNumberChanged(value: String) {
        _uiState.update { currentState ->
            currentState.copy(currentInputGroupNumber = value)
        }
    }

    fun signOut() {
        accountService.signOut()
    }

    fun toggleMenu() {
        _uiState.value = _uiState.value.copy(isMenuExpanded = !_uiState.value.isMenuExpanded)
    }

    fun onItemSelected(item: String) {
        _uiState.value = _uiState.value.copy(selectedSubgroupNumber = item, isMenuExpanded = false)
    }

    fun submitGroupChange() {
        if (!isCurrentGroupNumberCorrect()) {
            _uiState.update { currentState -> currentState.copy(isInputGroupNumberWrong = true) }
            _uiState.update { currentState -> currentState.copy(errorMessage = "Такой группы нет.\nФормат: 609-22") }
        } else {
            _uiState.update { currentState -> currentState.copy(isInputGroupNumberWrong = false) }
            _uiState.update { currentState -> currentState.copy(errorMessage = null) }

            viewModelScope.launch {
                try {
                    if (!_uiState.value.selectedSubgroupNumber.isNullOrEmpty() && !_uiState.value.selectedSubgroupNumber.isNullOrEmpty()){
                        val subgroupId = getSubgroupIdByGroupNumberAndSubgroupNameUseCase.invoke(
                            _uiState.value.currentInputGroupNumber,
                            _uiState.value.selectedSubgroupNumber.toString()
                        )
                        val groupNumber = if (subgroupId.isNotEmpty()) {
                            getGroupNumberAndSubgroupNameUseCase.invoke(subgroupId) ?: "Не указано"
                        } else {
                            "Не указано"
                        }
                        _uiState.update { currentState ->
                            currentState.copy(groupNumber = groupNumber)
                        }
                    }
                } catch (_: Exception) { }
            }


        }
    }
}