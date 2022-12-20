package com.example.expensetracker.domain.manage_tribe

interface TribeDataStore {
    suspend fun getTribeId():String
    suspend fun saveTribeId(tribe:String)
    suspend fun removeTribeId()
    suspend fun hasTribeId(): Boolean
}
