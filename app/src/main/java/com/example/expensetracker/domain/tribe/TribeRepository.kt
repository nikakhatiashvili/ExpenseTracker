package com.example.expensetracker.domain.tribe

interface TribeRepository {

    suspend fun createTribe(name: String)

    suspend fun joinTribe(inviteId:String)
}
