package com.example.expensetracker.domain.tasks

import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    suspend fun getTasks(): Flow<MutableList<Task>>
}
