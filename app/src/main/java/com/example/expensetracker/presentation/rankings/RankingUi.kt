package com.example.expensetracker.presentation.rankings

import com.example.expensetracker.databinding.RankingsFragmentBinding

interface RankingUi {
    fun apply(
        binding: RankingsFragmentBinding,
    )

    object Empty : RankingUi {
        override fun apply(binding: RankingsFragmentBinding) =
            Unit
    }

    class Loading : RankingUi {
        override fun apply(binding: RankingsFragmentBinding) {

        }
    }

    class SuccessUi() : RankingUi {
        override fun apply(binding: RankingsFragmentBinding) {

        }
    }
    class ErrorUi : RankingUi {
        override fun apply(binding: RankingsFragmentBinding) {

        }
    }
}
