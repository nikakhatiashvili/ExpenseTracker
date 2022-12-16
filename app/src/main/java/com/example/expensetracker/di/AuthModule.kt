package com.example.expensetracker.di

import com.example.expensetracker.data.auth.AuthRepositoryImpl
import com.example.expensetracker.data.tribe.TribeRepositoryImpl
import com.example.expensetracker.domain.auth.AuthRepository
import com.example.expensetracker.domain.tribe.TribeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AuthModule {

    @Binds
    fun bindsAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindsTribeRepository(tribeRepositoryImpl: TribeRepositoryImpl): TribeRepository
}
