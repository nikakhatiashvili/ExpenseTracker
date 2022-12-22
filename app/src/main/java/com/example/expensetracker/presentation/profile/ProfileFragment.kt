package com.example.expensetracker.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.R
import com.example.expensetracker.databinding.FragmentProfileBinding
import com.example.expensetracker.presentation.common.collectFlow
import com.example.expensetracker.presentation.common.toast
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
        collectFlow(viewModel.isInTribe){
            if (it) findNavController().navigate(R.id.action_profileFragment_to_tribeFragment)
            else requireContext().toast(requireContext().getString(R.string.notInTribe))
        }
        collectFlow(viewModel.createTribe){
            if (!it) {
                val dialog = DialogFragment()
                dialog.show(parentFragmentManager, requireContext().getString(R.string.dialog))

            }else{
                requireContext().toast(requireContext().getString(R.string.cant_create))
            }
        }
        collectFlow(viewModel.joinTribe){
            if (!it){
                val dialog = JoinDialogFragment()
                dialog.show(parentFragmentManager, requireContext().getString(R.string.dialog))
            }else{
                requireContext().toast(requireContext().getString(R.string.already_in_tribe))
            }
        }

    }

    private fun setupClickListeners() = with(binding) {
        btnJoinTribe.setOnClickListener {
            viewModel.goToJoinTribe()
        }
        btnCreateTribe.setOnClickListener {
            viewModel.checkTribeId()
        }
        btnLogOut.setOnClickListener {
            viewModel.logOut()
            val navHostFragment = parentFragment as NavHostFragment?
            val d = navHostFragment!!.requireParentFragment() as TabsFragment
            d.logOut()
        }
        btnManageTribe.setOnClickListener {
            viewModel.goToManageTribe()
        }
    }
}
