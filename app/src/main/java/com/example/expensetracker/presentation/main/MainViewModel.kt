package com.example.expensetracker.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.domain.auth.AuthDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val startDestinationMainRouter: StartDestinationMainRouter,
    private val authDataStore: AuthDataStore
) : ViewModel() {

    private val _destinationState = MutableStateFlow<Boolean>(false)
    val destinationState = _destinationState.asStateFlow()

    fun onAppLaunched() {
        viewModelScope.launch {
            if (authDataStore.hasUid()){
                _destinationState.emit(true)
                startDestinationMainRouter.setTabsAsStartDestination()
            }else{
                _destinationState.emit(false)
                startDestinationMainRouter.setSignInStartDestination()
            }
        }
    }
}
