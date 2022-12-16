package com.example.expensetracker.domain.tribe

interface TribeRepository {

    suspend fun createTribe(name: String)
}
