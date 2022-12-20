package com.example.expensetracker.presentation.transactions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.R
import com.example.expensetracker.databinding.FragmentTransactionsBinding
import com.example.expensetracker.presentation.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionsFragment : Fragment(R.layout.fragment_transactions) {

    private val binding by viewBinding(FragmentTransactionsBinding::bind)

    private val viewModel: TransactionsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setupCollects()
    }

    private fun setupClickListeners() = with(binding) {
        findNavController().navigate(R.id.action_global_signInFragment)
    }

    private fun setupCollects() {

    }
}
