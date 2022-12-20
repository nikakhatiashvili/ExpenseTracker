package com.example.expensetracker.di

import android.content.Context
import com.example.expensetracker.R
import com.example.expensetracker.common.Dispatchers
import com.example.expensetracker.common.ResourceManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Provides
    @Singleton
    fun provideDatabaseReference(resourceManager: ResourceManager): FirebaseDatabase =
        FirebaseDatabase.getInstance(resourceManager.provide(R.string.tribe_url))

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideResourceManager(@ApplicationContext context: Context): ResourceManager =
        ResourceManager.Base(context)

    @Provides
    @Singleton
    fun provideDispatchers(): Dispatchers = Dispatchers.Base()
}
