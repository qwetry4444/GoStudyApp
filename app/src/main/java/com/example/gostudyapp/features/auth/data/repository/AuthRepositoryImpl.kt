package com.example.gostudyapp.features.auth.data.repository

import com.example.gostudyapp.features.auth.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth

class AuthRepositoryImpl(
    private val auth: FirebaseAuth
) : AuthRepository   {

    var success: Boolean = false;

    override fun login(email: String, password: String) : Boolean{

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isComplete){
                    success = true
                } else {
                    success = false
                }
            }
        return success
    }

    override fun signUp(email: String, password: String) : Boolean{
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isComplete){
                    success = true
                } else {
                    success = false
                }
            }
        return success
    }


    override fun signOut(){
        auth.signOut()
    }

    sealed class AuthState{
        data object Authenticated: AuthState()
        data object Unauthenticated: AuthState()
        data object Loaded: AuthState()
        data class Error(val message: String) : AuthState()
    }
}

