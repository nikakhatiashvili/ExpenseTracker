package com.example.expensetracker.presentation.tabs

import androidx.lifecycle.ViewModel
import com.example.expensetracker.presentation.tabs.di.TabsComponentHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TabsViewModel @Inject constructor(
    private val tabsComponentHolder: TabsComponentHolder,

) : ViewModel() {

    init {
        tabsComponentHolder.create()
    }

    override fun onCleared() {
        tabsComponentHolder.destroy()
    }

//    fun onButtonClicked() {
//        peopleMainRouter.goToHome()
//    }
}
