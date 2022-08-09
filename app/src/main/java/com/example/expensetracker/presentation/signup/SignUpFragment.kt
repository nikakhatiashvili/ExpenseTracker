package com.example.expensetracker.presentation.signup

import android.os.Bundle
import android.system.Os.bind
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.expensetracker.R
import com.example.expensetracker.databinding.FragmentSignInBinding
import com.example.expensetracker.databinding.FragmentSignUpBinding
import com.example.expensetracker.presentation.common.collectFlow
import com.example.expensetracker.presentation.common.gone
import com.example.expensetracker.presentation.common.viewBinding
import com.example.expensetracker.presentation.common.visible
import com.example.expensetracker.presentation.signin.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val binding by viewBinding(FragmentSignUpBinding::bind)

    private val viewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setupCollects()
    }

    private fun setupClickListeners() = with(binding){
        tvSignIn.setOnClickListener {
            viewModel.goToSignIn()
        }
        btnSignUp.setOnClickListener {
            val email = etEmail.editText?.text.toString()
            val password = etPassword.editText?.text.toString()
            val secondPassword = etRepeatPassword.editText?.text.toString()
            viewModel.signUp(email,password,secondPassword)
        }
    }

    private fun setupCollects() {
        collectFlow(viewModel.signUpResultEvent) {
            it.apply(context)
        }
        val progressBar = binding.loadingProgressBar
        collectFlow(viewModel.isLoading) { isLoading ->
             if (isLoading) progressBar.visible() else progressBar.gone()
        }
    }
}

