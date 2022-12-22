package com.example.expensetracker.presentation.manage_tribe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.example.expensetracker.R
import com.example.expensetracker.databinding.TribeDialogBinding
import com.example.expensetracker.domain.tasks.Task
import com.example.expensetracker.presentation.common.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TribeDialogFragment : DialogFragment() {

    private var _binding: TribeDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TribeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TribeDialogBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupClicks()
        return root
    }

    private fun setupClicks() {
        binding.create.setOnClickListener {
            val desc = binding.etDescription.editText?.text
            val name = binding.etName.editText?.text
            if (desc.isNullOrEmpty() || name.isNullOrEmpty()) {
                requireContext().toast(requireContext().getString(R.string.All_fields_must_me_full))
            } else {
                viewModel.addTask(Task(name = name.toString(), description = desc.toString()))
                dismiss()
            }

        }
    }
}
