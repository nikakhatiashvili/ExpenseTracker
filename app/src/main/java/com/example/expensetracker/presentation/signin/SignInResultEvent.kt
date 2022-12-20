package com.example.expensetracker.presentation.signin

import android.content.Context
import com.example.expensetracker.R
import com.example.expensetracker.presentation.common.showMessage

interface SignInResultEvent {

    fun apply(context: Context?)

    class Success(private val signInMainRouter: SignInMainRouter, private val name: String) :
        SignInResultEvent {
        override fun apply(context: Context?) {
            context?.showMessage(context.resources.getString(R.string.sign_in_welcome, name))
            signInMainRouter.goToTabs()
        }
    }

    class Error(private val errorMessage: String) : SignInResultEvent {
        override fun apply(context: Context?) {
            context?.showMessage(errorMessage)
        }
    }
}
