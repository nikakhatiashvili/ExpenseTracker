package com.example.expensetracker.presentation.profile

import androidx.lifecycle.*
import com.example.expensetracker.domain.auth.AuthDataStore
import com.example.expensetracker.domain.tribe.TribeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val tribeRepository: TribeRepository,
    private val authDataStore: AuthDataStore
) : ViewModel() {

    fun createTribe(name:String) {
        viewModelScope.launch(Dispatchers.IO) {
           tribeRepository.createTribe(name)
        }
    }
    fun logOut() {
        viewModelScope.launch(Dispatchers.IO) {
            authDataStore.removeUid()
        }
    }
}
