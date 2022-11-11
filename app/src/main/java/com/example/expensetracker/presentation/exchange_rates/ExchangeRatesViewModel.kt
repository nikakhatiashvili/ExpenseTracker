package com.example.expensetracker.presentation.exchange_rates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.common.Dispatchers
import com.example.expensetracker.domain.currency_rates.CurrencyRepository
import com.example.expensetracker.common.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ExchangeRatesViewModel @Inject constructor(
    private val dispatchers: Dispatchers,
    private val currencyRepository: CurrencyRepository
): ViewModel() {

    private val _currencyState = MutableStateFlow<CurrencyUi>(CurrencyUi.Empty)
    val currencyState = _currencyState.asStateFlow()

     fun getExchangeRates() {
         dispatchers.launchBackground(viewModelScope) {
            _currencyState.emit(CurrencyUi.Loading())
            when (val result = currencyRepository.getCommercials()) {
                is Result.ApiSuccess -> _currencyState.emit(CurrencyUi.SuccessUi(result.data))
                is Result.ApiError -> _currencyState.emit(CurrencyUi.ErrorUi())
                is Result.ApiException -> _currencyState.emit(CurrencyUi.ErrorUi())
            }
        }
    }
}
