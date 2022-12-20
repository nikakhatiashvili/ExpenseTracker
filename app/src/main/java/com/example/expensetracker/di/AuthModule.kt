package com.example.expensetracker.di

import com.example.expensetracker.data.auth.AuthRepositoryImpl
import com.example.expensetracker.data.manage_tribe.ManageTribeRepositoryImpl
import com.example.expensetracker.data.manage_tribe.TribeIdImpl
import com.example.expensetracker.data.tribe.TribeRepositoryImpl
import com.example.expensetracker.domain.auth.AuthRepository
import com.example.expensetracker.domain.manage_tribe.ManageTribeRepository
import com.example.expensetracker.domain.manage_tribe.TribeIdRepository
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

    @Binds
    fun bindsTribeIdRepository(tribeIdImpl: TribeIdImpl): TribeIdRepository

    @Binds
    fun bindsManageTribeRepository
                (manageTribeRepositoryImpl: ManageTribeRepositoryImpl): ManageTribeRepository
}
