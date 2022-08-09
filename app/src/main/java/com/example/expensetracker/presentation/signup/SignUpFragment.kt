package com.example.expensetracker.presentation.signup

import android.os.Bundle
import android.system.Os.bind
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.expensetracker.R
import com.example.expensetracker.databinding.FragmentSignInBinding
import com.example.expensetracker.databinding.FragmentSignUpBinding
import com.example.expensetracker.presentation.common.viewBinding
import com.example.expensetracker.presentation.signin.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val binding by viewBinding(FragmentSignUpBinding::bind)

    private val viewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
//        googleSignIn.callback = viewModel::handleSignIn
//        setupCollects()
    }

    private fun setupClickListeners() {
        binding.tvSignIn.setOnClickListener {
            viewModel.goToSignIn()
        }
    }

//    private fun setupCollects() {
//        collectFlow(viewModel.signInResultEvent) {
//            it.apply(context)
//        }
//
//        collectFlow(viewModel.isLoading) { isLoading ->
//            binding.loadingProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
//        }
//    }
}

