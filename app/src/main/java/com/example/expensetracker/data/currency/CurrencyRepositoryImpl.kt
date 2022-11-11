package com.example.expensetracker.data.currency

import com.example.expensetracker.data.network.CurrencyService
import com.example.expensetracker.domain.currency_rates.Currency
import com.example.expensetracker.domain.currency_rates.CurrencyRepository
import com.example.expensetracker.common.Result
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyService: CurrencyService
):CurrencyRepository {
    override suspend fun getCommercials(): Result<Currency> {
      return  currencyService.getExchange()
    }
}
