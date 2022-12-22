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

    private val _joinTribe = MutableSharedFlow<Boolean>()
    val joinTribe: MutableSharedFlow<Boolean> get() = _joinTribe

    fun createTribe(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            tribeRepository.createTribe(name)
        }
    }

    fun goToManageTribe() {
        viewModelScope.launch(Dispatchers.IO) {
            _isInTribe.emit(hasTribeId())
        }
    }
    fun joinTribe(inviteId:String){
        viewModelScope.launch(Dispatchers.IO) {
            tribeRepository.joinTribe(inviteId)
        }
    }
    fun goToJoinTribe(){
        viewModelScope.launch(Dispatchers.IO) {
            _joinTribe.emit(hasTribeId())
        }
    }

    fun checkTribeId() {
        viewModelScope.launch(Dispatchers.IO) {
            _createTribe.emit(hasTribeId())
        }
    }
    private suspend fun hasTribeId(): Boolean {
        return tribeDataStore.hasTribeId()
    }

    fun logOut() {
        viewModelScope.launch(Dispatchers.IO) {
            authDataStore.removeUid()
            tribeDataStore.removeTribeId()
        }
    }
}
