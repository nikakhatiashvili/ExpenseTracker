package com.example.expensetracker.presentation.exchange_rates

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.expensetracker.R
import com.example.expensetracker.databinding.ExchangeRatesFragmentBinding
import com.example.expensetracker.databinding.FragmentSignInBinding
import com.example.expensetracker.presentation.common.collectFlow
import com.example.expensetracker.presentation.common.gone
import com.example.expensetracker.presentation.common.viewBinding
import com.example.expensetracker.presentation.common.visible
import com.example.expensetracker.presentation.signin.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExchangeRatesFragment : Fragment(R.layout.exchange_rates_fragment){

    private val binding by viewBinding(ExchangeRatesFragmentBinding::bind)

    private val viewModel: ExchangeRatesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() = with(binding) {

    }
}
