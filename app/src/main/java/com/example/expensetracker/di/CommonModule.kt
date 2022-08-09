package com.example.expensetracker.di

import android.content.Context
import com.example.expensetracker.domain.common.Dispatchers
import com.example.expensetracker.domain.common.ResourceManager
import com.google.firebase.auth.FirebaseAuth
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
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideResourceManager(@ApplicationContext context: Context): ResourceManager =
        ResourceManager.Base(context)

    @Provides
    @Singleton
    fun provideDispatchers(): Dispatchers = Dispatchers.Base()
}
