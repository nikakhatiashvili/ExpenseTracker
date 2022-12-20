package com.example.expensetracker.data.ranking

import com.example.expensetracker.data.network.RankingService
import com.example.expensetracker.domain.rankings.RankingRepository
import javax.inject.Inject

class RankingsRepositoryImpl @Inject constructor(
    private val rankingService: RankingService
):RankingRepository {
    override suspend fun getRanks(){

    }
}
