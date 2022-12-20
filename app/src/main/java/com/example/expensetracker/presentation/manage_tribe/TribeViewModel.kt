package com.example.expensetracker.presentation.manage_tribe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.domain.manage_tribe.ManageTribeRepository
import com.example.expensetracker.domain.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TribeViewModel @Inject constructor(
    private val manageTribeRepository: ManageTribeRepository
) : ViewModel() {

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            manageTribeRepository.addTask(task)
        }
    }
}
