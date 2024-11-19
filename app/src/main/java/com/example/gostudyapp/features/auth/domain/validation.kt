package com.example.gostudyapp.features.auth.domain

import android.util.Patterns
import java.util.regex.Pattern

private const val MIN_PASS_LENGTH = 6
private const val GROUP_NUMBER_LENGTH = 6
private const val PASS_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"
private const val GROUP_NUMBER_PATTERN = "^\\d{3}-\\d{2}$"


fun String.isValidEmail(): Boolean {
    return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    return this.isNotBlank()
            && this.length >= MIN_PASS_LENGTH
            && Pattern.compile(PASS_PATTERN).matcher(this).matches()
}

fun String.isValidGroupNumber() : Boolean{
    return this.isNotBlank()
            && this.length == GROUP_NUMBER_LENGTH
            && Pattern.compile(GROUP_NUMBER_PATTERN).matcher(this).matches()
}