package com.example.expensetracker.presentation.exchange_rates

import android.widget.ProgressBar
import com.example.expensetracker.domain.currency_rates.Currency
import com.example.expensetracker.presentation.common.gone
import com.example.expensetracker.presentation.common.visible

interface CurrencyUi {
    fun apply(adapter: CurrencyRatesAdapter,loadingProgressBar:ProgressBar)

    object Empty : CurrencyUi {
        override fun apply(adapter: CurrencyRatesAdapter, loadingProgressBar: ProgressBar) = Unit
    }

    class Loading : CurrencyUi {
        override fun apply(adapter: CurrencyRatesAdapter, loadingProgressBar: ProgressBar) {
            loadingProgressBar.visible()
        }
    }

    class SuccessUi(private val currency: Currency) : CurrencyUi {
        override fun apply(adapter: CurrencyRatesAdapter, loadingProgressBar: ProgressBar) {
            adapter.data = currency.commercialRatesList!!
            loadingProgressBar.gone()
        }
    }

    class ErrorUi : CurrencyUi {
        override fun apply(adapter: CurrencyRatesAdapter, loadingProgressBar: ProgressBar) {
            loadingProgressBar.gone()
        }
    }
}
