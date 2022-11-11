package com.example.expensetracker.data.network

import com.example.expensetracker.domain.currency_rates.Currency
import com.example.expensetracker.common.Result
import retrofit2.http.GET
import retrofit2.http.Headers

interface CurrencyService {

    @Headers("apikey: tBXoEW51JcJJYOa6PDNwnPg3ikNSACux")
    @GET("exchange-rates/commercial")
    suspend fun getExchange(): Result<Currency>
}
