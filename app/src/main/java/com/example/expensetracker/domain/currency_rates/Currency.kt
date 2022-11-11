package com.example.expensetracker.domain.currency_rates

data class Currency(
    val base: String?,
    val commercialRatesList: List<CommercialRates>?
)
