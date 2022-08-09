package com.example.expensetracker.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.domain.auth.AuthDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val router: StartDestinationMainRouter,
    private val authDataStore: AuthDataStore
) : ViewModel() {

    fun onAppLaunched() {
        viewModelScope.launch {
            if (authDataStore.hasToken()){
                router.setTabsAsStartDestination()
            }else{
                router.setSignInAsStartDestination()
            }
        }
    }
}
