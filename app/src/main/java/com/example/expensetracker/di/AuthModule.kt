package com.example.expensetracker.di

import com.example.expensetracker.data.AuthRepositoryImpl
import com.example.expensetracker.domain.auth.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AuthModule {

    @Binds
    fun bindsAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
}
