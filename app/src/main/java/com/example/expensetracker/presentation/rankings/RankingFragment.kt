package com.example.expensetracker.presentation.rankings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.expensetracker.R
import com.example.expensetracker.databinding.RankingsFragmentBinding
import com.example.expensetracker.presentation.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankingFragment : Fragment(R.layout.rankings_fragment){

    private val binding by viewBinding(RankingsFragmentBinding::bind)

    private val viewModel: RankingsViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setupCollects()
    }
    private fun setupCollects(){

    }
    private fun setupClickListeners() = with(binding) {
        viewModel.getRankings()
    }
}
