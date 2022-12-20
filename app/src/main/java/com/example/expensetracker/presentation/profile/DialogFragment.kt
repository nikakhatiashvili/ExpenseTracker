package com.example.expensetracker.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.expensetracker.R
import com.example.expensetracker.databinding.FragmentTribeDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DialogFragment : DialogFragment() {

    private var _binding: FragmentTribeDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTribeDialogBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupClicks()
        return root
    }

    private fun setupClicks() {
        binding.create.setOnClickListener {
            setFragmentResult(
                requireContext().getString(R.string.key3),
                bundleOf(
                    requireContext().getString(R.string.key4)
                            to binding.etEmail.editText?.text.toString()
                )
            )
            dismiss()
        }
    }
}
