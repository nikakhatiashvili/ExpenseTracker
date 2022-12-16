package com.example.expensetracker.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.domain.tasks.TasksRepository
import com.example.expensetracker.presentation.common.collect
import com.example.expensetracker.presentation.signin.SignInResultEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tasksRepository: TasksRepository
) : ViewModel() {

    private val _signInResultEvent = Channel<SignInResultEvent>()
    val signInResultEvent = _signInResultEvent.receiveAsFlow()

    fun getTasks() {
        viewModelScope.launch {
           collect(tasksRepository.getTasks()){

           }
        }
    }

}
