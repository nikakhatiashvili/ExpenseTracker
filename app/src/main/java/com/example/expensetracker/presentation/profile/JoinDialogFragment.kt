package com.example.expensetracker.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.expensetracker.databinding.FragmentTribeDialogBinding
import com.example.expensetracker.databinding.JoinTribeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JoinDialogFragment : DialogFragment() {

    private var _binding: JoinTribeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = JoinTribeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupClicks()
        return root
    }

    private fun setupClicks() {
        binding.btnJoin.setOnClickListener {
            viewModel.joinTribe(binding.inviteUrl.editText?.text.toString())
            dismiss()
        }
    }
}

