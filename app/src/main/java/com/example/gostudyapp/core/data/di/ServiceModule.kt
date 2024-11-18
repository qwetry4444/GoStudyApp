package com.example.gostudyapp.core.data.di

import com.example.gostudyapp.core.data.auth.IAccountService
import com.example.gostudyapp.core.data.auth.impl.AccountService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds abstract fun provideAccountService(impl: AccountService): IAccountService
}