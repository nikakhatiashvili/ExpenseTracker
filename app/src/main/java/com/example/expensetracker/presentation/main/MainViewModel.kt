package com.example.expensetracker.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val router: StartDestinationMainRouter,
) : ViewModel() {

    fun onAppLaunched() {
        viewModelScope.launch {
            router.setSignInAsStartDestination()
        }
    }
}
