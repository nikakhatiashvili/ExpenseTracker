package com.example.expensetracker.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.expensetracker.R
import com.example.expensetracker.databinding.FragmentProfileBinding
import com.example.expensetracker.presentation.common.viewBinding
import com.example.expensetracker.presentation.tabs.TabsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentProfileBinding::bind)

    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setupCollects()
    }

    private fun setupCollects() {

    }

    private fun setupClickListeners() = with(binding) {
        binding.btnCreateTribe.setOnClickListener {
            val dialog = DialogFragment()
            dialog.show(parentFragmentManager, "CustomDialog")
            setFragmentResultListener("requestKey") { requestKey, bundle ->
                val name = bundle.getString("bundleKey")
                viewModel.createTribe(name!!)
            }
        }
        binding.btnLogOut.setOnClickListener {
            viewModel.logOut()
            val navHostFragment = parentFragment as NavHostFragment?
            val d = navHostFragment!!.requireParentFragment() as TabsFragment
            d.logOut()
        }
        binding.btnManageTribe.setOnClickListener {

        }
    }
}
