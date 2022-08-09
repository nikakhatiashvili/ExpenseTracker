package com.example.expensetracker.presentation.tabs.di

import com.example.expensetracker.R
import com.example.expensetracker.Route
import com.example.expensetracker.RouteProvider
import com.example.expensetracker.presentation.detail.DetailRouter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject

@TabsScope
class TabsRouterImpl @Inject constructor() : RouteProvider, DetailRouter {

    private val _route = MutableStateFlow<Route?>(null)
    override val route: Flow<Route> = _route.asStateFlow().filterNotNull()

    override fun onRouteExecuted() {
        _route.value = null
    }

    override fun goToHome() {
        _route.value = { navController ->
            navController.navigate(R.id.action_tabsFragment_to_detailFragment)
        }
    }
//
//    override fun goToAccountDetails() {
//        _route.value = { navController ->
//            navController.navigate(R.id.action_accountsFragment_to_accountDetailsFragment)
//        }
//    }
}
