package com.example.expensetracker.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInMainRouter: SignInMainRouter
) : ViewModel() {

//    private val _signInResultEvent = Channel<SignInResultEvent>()
//    val signInResultEvent = _signInResultEvent.receiveAsFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun goToSignUp(){
        signInMainRouter.goToSignUp()
    }

//    fun handleSignIn(result: GoogleSignInResult) {
////        viewModelScope.launch {
////            when (result) {
////                is GoogleSignInResult.Error -> _signInResultEvent.send(SignInResultEvent.Error(signInMainRouter))
////                is GoogleSignInResult.Success -> handleAccessTokenRequest(result.account.idToken.toString())
////            }
////        }
//        signInMainRouter.goToTabs()
//    }

//    private fun handleAccessTokenRequest(idToken: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            _isLoading.value = true
//            when (val result = authRepository.authenticate(idToken)) {
//                is Result.ApiError -> Timber.d(result.message, result.code)
//                is Result.ApiException -> Timber.d(result.e)
//                is Result.ApiSuccess -> _signInResultEvent.send(SignInResultEvent.Success(signInMainRouter))
//            }
//            _isLoading.value = false
//        }
//    }
}
