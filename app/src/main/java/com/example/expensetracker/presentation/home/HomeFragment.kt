package com.example.expensetracker.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensetracker.R
import com.example.expensetracker.databinding.FragmentHomeBinding
import com.example.expensetracker.presentation.common.collectFlow
import com.example.expensetracker.presentation.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home){
    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var adapter: TasksAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setupCollects()
    }
    private fun setupCollects(){
        adapter = TasksAdapter()
        binding.tasksRecyclerview.adapter = adapter
        binding.tasksRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        collectFlow(viewModel.taskResultEvent){
            adapter.data = it
        }
    }
    private fun setupClickListeners() = with(binding) {
        viewModel.getTasks()
    }
}
