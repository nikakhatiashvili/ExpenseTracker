package com.example.expensetracker.presentation.signup

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.common.Dispatchers
import com.example.expensetracker.common.Result
import com.example.expensetracker.domain.auth.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpMainRouter: SignUpMainRouter,
    private val provideDispatchers: Dispatchers,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _signUpResultEvent = Channel<SignUpResultEvent>()
    val signUpResultEvent = _signUpResultEvent.receiveAsFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun goToSignIn(){
        signUpMainRouter.goToSignIn()
    }

    fun signUp(email:String,password:String,repeatPassword:String){
        provideDispatchers.launchBackground(viewModelScope){
            _isLoading.value = true
            when(val result = signUpUseCase.invoke(email, password, repeatPassword)){
                is Result.ApiSuccess -> {
                    _signUpResultEvent.send(SignUpResultEvent.Success(signUpMainRouter,result.data.user?.email.toString()))
                }
                is Result.ApiError -> _signUpResultEvent.send(SignUpResultEvent.Error(result.message.toString()))
                is Result.ApiException -> d("error",result.e.message.toString())
            }
            _isLoading.value = false
        }
    }
}
