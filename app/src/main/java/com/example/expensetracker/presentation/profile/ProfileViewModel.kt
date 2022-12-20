package com.example.expensetracker.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.domain.auth.AuthDataStore
import com.example.expensetracker.domain.manage_tribe.TribeDataStore
import com.example.expensetracker.domain.tribe.TribeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val tribeRepository: TribeRepository,
    private val authDataStore: AuthDataStore,
    private val tribeDataStore: TribeDataStore
) : ViewModel() {

    private val _isInTribe = MutableSharedFlow<Boolean>()
    val isInTribe: MutableSharedFlow<Boolean> get() = _isInTribe

    private val _createTribe = MutableSharedFlow<Boolean>()
    val createTribe: MutableSharedFlow<Boolean> get() = _createTribe

    fun createTribe(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            tribeRepository.createTribe(name)
        }
    }

    fun goToManageTribe() {
        viewModelScope.launch(Dispatchers.IO) {
            _isInTribe.emit(tribeDataStore.hasTribeId())
        }
    }

    fun createTribe() {
        viewModelScope.launch(Dispatchers.IO) {
            _createTribe.emit(tribeDataStore.hasTribeId())
        }
    }

    fun logOut() {
        viewModelScope.launch(Dispatchers.IO) {
            authDataStore.removeUid()
            tribeDataStore.removeTribeId()
        }
    }
}
