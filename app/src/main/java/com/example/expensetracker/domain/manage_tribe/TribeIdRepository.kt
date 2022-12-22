package com.example.expensetracker.domain.manage_tribe

import com.example.expensetracker.common.Result
import kotlinx.coroutines.flow.Flow

interface TribeIdRepository {

    suspend fun saveTribeId(): Flow<Result<Boolean>>
}
