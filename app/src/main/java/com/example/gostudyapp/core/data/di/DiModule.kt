package com.example.gostudyapp.core.data.di

import com.example.gostudyapp.features.auth.data.repository.AuthRepositoryImpl
import com.example.gostudyapp.features.auth.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiModule{

    @Provides
    @Singleton
    fun provideAuthRepository() : AuthRepository =
        AuthRepositoryImpl(FirebaseAuth.getInstance())

}