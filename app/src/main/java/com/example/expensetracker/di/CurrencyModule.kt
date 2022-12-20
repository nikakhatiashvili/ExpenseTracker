package com.example.expensetracker.di

import com.example.expensetracker.data.ranking.RankingsRepositoryImpl
import com.example.expensetracker.data.tasks.TasksRepositoryImpl
import com.example.expensetracker.domain.rankings.RankingRepository
import com.example.expensetracker.domain.tasks.TasksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CurrencyModule {

    @Binds
    fun bindRanksRepositoryImpl(currencyRepositoryImpl: RankingsRepositoryImpl): RankingRepository

    @Binds
    fun bindTasksRepositoryImpl(tasksRepositoryImpl: TasksRepositoryImpl): TasksRepository
}
