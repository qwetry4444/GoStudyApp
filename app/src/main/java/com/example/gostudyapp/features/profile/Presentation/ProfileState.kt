package com.example.gostudyapp.features.profile.Presentation

data class ProfileState(
    var groupNumber: String = "",
    var subgroup: String = "",
    var userLogin: String = "",
    var userGroupNumber: String = "",
    var userSubgroup: String = "",
    var currentInputGroupNumber: String = "",
    var selectedSubgroupNumber: String? = null,
    var isMenuExpanded: Boolean = false,
    var isInputGroupNumberWrong: Boolean = false,
    var errorMessage: String? = null
)