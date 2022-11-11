package com.example.expensetracker.di

import com.example.expensetracker.data.currency.CurrencyRepositoryImpl
import com.example.expensetracker.domain.currency_rates.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CurrencyModule {

    @Binds
    fun bindCurrencyRepositoryImpl(currencyRepositoryImpl: CurrencyRepositoryImpl): CurrencyRepository

}
