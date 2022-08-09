package com.example.expensetracker.presentation.signup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpMainRouter: SignUpMainRouter
) : ViewModel() {

//    private val _signInResultEvent = Channel<SignInResultEvent>()
//    val signInResultEvent = _signInResultEvent.receiveAsFlow()

    fun goToSignIn(){
        signUpMainRouter.goToSignIn()
    }

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

}

