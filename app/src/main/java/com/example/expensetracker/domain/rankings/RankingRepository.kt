package com.example.expensetracker.domain.rankings

interface RankingRepository {

    suspend fun getRanks()
}
