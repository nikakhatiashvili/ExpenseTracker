package com.example.expensetracker.presentation.manage_tribe

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.example.expensetracker.R
import com.example.expensetracker.databinding.FragmentTribeBinding
import com.example.expensetracker.domain.tasks.Task
import com.example.expensetracker.presentation.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TribeFragment : Fragment(R.layout.fragment_tribe) {
    private val binding by viewBinding(FragmentTribeBinding::bind)

    private val viewModel: TribeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setupCollects()
    }

    private fun setupCollects() {

    }

    private fun setupClickListeners() = with(binding) {
        create.setOnClickListener {
            val dialog = TribeDialogFragment()
            dialog.show(parentFragmentManager, requireContext().getString(R.string.dialog))
            setFragmentResultListener(requireContext().getString(R.string.Key1)) { requestKey, bundle ->
                val name = bundle.getParcelable<Task>(requireContext().getString(R.string.key2))
                viewModel.addTask(name!!)
            }
        }
    }
}
