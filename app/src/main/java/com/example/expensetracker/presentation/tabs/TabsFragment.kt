package com.example.expensetracker.presentation.tabs

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.expensetracker.R
import com.example.expensetracker.databinding.FragmentTabsBinding
import com.example.expensetracker.navigate
import com.example.expensetracker.presentation.common.collectFlow
import com.example.expensetracker.presentation.common.viewBinding
import com.example.expensetracker.presentation.tabs.di.TabsComponentHolder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TabsFragment : Fragment(R.layout.fragment_tabs) {

    private val binding by viewBinding(FragmentTabsBinding::bind)

    @Inject
    lateinit var tabsComponentHolder: TabsComponentHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModels<TabsViewModel>().value // init view model
        setupBackPressHandler()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController =
            (childFragmentManager.findFragmentById(R.id.tabs_host_fragment) as NavHostFragment).navController
        binding.bottomNavigation.setupWithNavController(navController)
        tabsComponentHolder.getRouteProvider()?.run {
            collectFlow(route) { route ->
                navController.navigate(route)
                onRouteExecuted()
            }
        }
    }
    fun logOut(){
        findNavController().navigate(R.id.action_global_signInFragment)
    }

    private fun setupBackPressHandler() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            val firstTabId = binding.bottomNavigation.menu.getItem(0).itemId
            val navHostFragment =
                childFragmentManager.findFragmentById(R.id.tabs_host_fragment) as NavHostFragment
            val backStackCount = navHostFragment.childFragmentManager.backStackEntryCount
            when {
                binding.bottomNavigation.selectedItemId != firstTabId && backStackCount <= 1 -> {
                    binding.bottomNavigation.selectedItemId = firstTabId
                }
                binding.bottomNavigation.selectedItemId == firstTabId && backStackCount == 0 -> {
                    isEnabled = false
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                    isEnabled = true
                }
                else -> navHostFragment.navController.popBackStack()
            }
        }
    }
}
