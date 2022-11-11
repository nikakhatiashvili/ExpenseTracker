package com.example.expensetracker.domain.currency_rates

import com.example.expensetracker.common.Result

interface CurrencyRepository {

    suspend fun getCommercials(): Result<Currency>
}
