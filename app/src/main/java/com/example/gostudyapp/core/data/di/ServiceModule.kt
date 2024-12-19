package com.example.gostudyapp.core.data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    //@Binds abstract fun provideAccountService(impl: AccountService): IAccountService
    //@Binds abstract fun provideAccountService(impl: AccountService): AccountService
}