package com.example.expensetracker.presentation.signin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.expensetracker.R
import com.example.expensetracker.databinding.FragmentSignInBinding
import com.example.expensetracker.presentation.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val binding by viewBinding(FragmentSignInBinding::bind)

    private val viewModel: SignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
//        googleSignIn.callback = viewModel::handleSignIn
//        setupCollects()
    }

    private fun setupClickListeners() {

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
