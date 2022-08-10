package com.example.expensetracker.domain.auth

interface AuthDataStore {

    suspend fun saveUid(token: String)

    suspend fun hasUid(): Boolean

    suspend fun removeUid()

    suspend fun getUid(): String
}
