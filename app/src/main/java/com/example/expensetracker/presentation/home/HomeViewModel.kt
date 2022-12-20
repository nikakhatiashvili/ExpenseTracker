package com.example.expensetracker.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.domain.tasks.Task
import com.example.expensetracker.domain.tasks.TasksRepository
import com.example.expensetracker.presentation.common.collect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tasksRepository: TasksRepository
) : ViewModel() {

    private val _taskResultEvent = MutableStateFlow<List<Task>>(emptyList())
    val taskResultEvent : StateFlow<List<Task>> = _taskResultEvent.asStateFlow()

    fun getTasks() {
        viewModelScope.launch {
           collect(tasksRepository.getTasks()){
                _taskResultEvent.tryEmit(it)
           }
        }
    }
}
