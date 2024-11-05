package com.example.gostudyapp.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val authState = MutableLiveData<AuthState>()
    val _authState: LiveData<AuthState> = authState

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus(){
        if (auth.currentUser == null){
            authState.value = AuthState.Unauthenticated
        } else {
            authState.value = AuthState.Authenticated
        }
    }

    fun login(email: String, password: String){
        if (email.isEmpty() || password.isEmpty()){
            authState.value = AuthState.Error("Заполните поля!")
        }

        authState.value = AuthState.Loaded
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isComplete){
                    authState.value = AuthState.Authenticated
                } else {
                    authState.value = AuthState.Error("Почта или пароль неверны")
                }
            }
    }

    fun singUp(email: String, password: String){
        if (email.isEmpty() || password.isEmpty()){
            authState.value = AuthState.Error("Заполните поля!")
        }

        authState.value = AuthState.Loaded
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isComplete){
                    authState.value = AuthState.Authenticated
                } else {
                    authState.value = AuthState.Error("Почта или пароль неверны")
                }
            }
    }

    fun signOut(){
        auth.signOut()
        authState.value = AuthState.Unauthenticated
    }
}

sealed class AuthState{
    data object Authenticated: AuthState()
    data object Unauthenticated: AuthState()
    data object Loaded: AuthState()
    data class Error(val message: String) : AuthState()
}