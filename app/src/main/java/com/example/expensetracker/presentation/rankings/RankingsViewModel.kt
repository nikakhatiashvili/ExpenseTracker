package com.example.expensetracker.presentation.rankings

import androidx.lifecycle.ViewModel
import com.example.expensetracker.common.Dispatchers
import com.example.expensetracker.domain.rankings.RankingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RankingsViewModel @Inject constructor(
    private val dispatchers: Dispatchers,
    private val currencyRepository: RankingRepository
) : ViewModel() {

//    private val _currencyState = MutableStateFlow<RankingUi>(RankingUi.Empty)
//    val currencyState = _currencyState.asStateFlow()


    fun checkZero(string: String) = string.first().toString() != "0"
    fun getRankings() {
//        dispatchers.launchBackground(viewModelScope) {
//            _currencyState.emit(RankingUi.Loading())
//            when (val result = currencyRepository.getCommercials()) {
//                is Result.ApiSuccess -> _currencyState.emit(RankingUi.SuccessUi(result.data))
//                is Result.ApiError -> _currencyState.emit(RankingUi.ErrorUi())
//                is Result.ApiException -> _currencyState.emit(RankingUi.ErrorUi())
//            }
//        }
    }
}
