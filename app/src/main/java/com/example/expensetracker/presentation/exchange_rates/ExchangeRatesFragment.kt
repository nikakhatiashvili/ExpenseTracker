package com.example.expensetracker.presentation.exchange_rates

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensetracker.R
import com.example.expensetracker.databinding.ExchangeRatesFragmentBinding
import com.example.expensetracker.presentation.common.collectFlow
import com.example.expensetracker.presentation.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExchangeRatesFragment : Fragment(R.layout.exchange_rates_fragment){

    private val binding by viewBinding(ExchangeRatesFragmentBinding::bind)

    private val viewModel: ExchangeRatesViewModel by viewModels()
    private lateinit var adapter: CurrencyRatesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setupCollects()
    }
    private fun setupCollects(){
        adapter = CurrencyRatesAdapter()
        binding.currencyRates.adapter = adapter
        binding.currencyRates.layoutManager = LinearLayoutManager(requireContext())
        collectFlow(viewModel.currencyState){ ui ->
            ui.apply(adapter,binding.loadingProgressBar)
        }
    }
    private fun setupClickListeners() = with(binding) {
        viewModel.getExchangeRates()
    }
}
