package com.example.expensetracker.domain.auth

interface AuthDataStore {

    suspend fun saveToken(token: String)

    suspend fun hasToken(): Boolean

    suspend fun removeToken()

    suspend fun getToken(): String
}
