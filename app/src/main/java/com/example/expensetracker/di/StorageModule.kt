package com.example.expensetracker.di

import com.example.expensetracker.data.auth.AuthDataStoreImpl
import com.example.expensetracker.domain.auth.AuthDataStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface StorageModule {

    @Binds
    fun providesAuthDataStore(impl: AuthDataStoreImpl): AuthDataStore
}
