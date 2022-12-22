package com.example.expensetracker.presentation.signin

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.R
import com.example.expensetracker.common.Dispatchers
import com.example.expensetracker.common.ResourceManager
import com.example.expensetracker.common.Result
import com.example.expensetracker.domain.auth.SignInUseCase
import com.example.expensetracker.domain.manage_tribe.TribeDataStore
import com.example.expensetracker.domain.manage_tribe.TribeIdRepository
import com.example.expensetracker.presentation.common.collect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInMainRouter: SignInMainRouter,
    private val dispatchers: Dispatchers,
    private val signInUseCase: SignInUseCase,
    private val resourceManager: ResourceManager,
    private val tribeIdRepository: TribeIdRepository
) : ViewModel() {

    private val _signInResultEvent = Channel<SignInResultEvent>()
    val signInResultEvent = _signInResultEvent.receiveAsFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun goToSignUp(){
        signInMainRouter.goToSignUp()
    }

    fun signIn(email:String,password:String){
        dispatchers.launchBackground(viewModelScope){
            _isLoading.value = true
           when(val result =  signInUseCase.invoke(email, password)){
               is Result.ApiSuccess -> {
                   saveTribeId()
               }
               is Result.ApiError -> _signInResultEvent.send(SignInResultEvent.Error(result.message.toString()))
               is Result.ApiException -> d(resourceManager.provide(R.string.error),result.e.message.toString())
           }
            _isLoading.value = false
        }
    }
    private suspend fun saveTribeId(){
        collect(tribeIdRepository.saveTribeId()){
            when(it) {
                is Result.ApiSuccess -> {
                    _signInResultEvent.send(SignInResultEvent.Success(signInMainRouter))
                }
            }
        }
    }
}
