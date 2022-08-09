@file:SuppressWarnings("TooManyFunctions")

package com.example.expensetracker.presentation.main
import com.example.expensetracker.R
import com.example.expensetracker.Route
import com.example.expensetracker.StartDestinationAndRouteProvider
import com.example.expensetracker.presentation.signin.SignInMainRouter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRouterImpl @Inject constructor() : StartDestinationAndRouteProvider, StartDestinationMainRouter,
    SignInMainRouter {

    private val _startDestination = MutableStateFlow<Int?>(null)
    override val startDestination: Flow<Int> = _startDestination.asStateFlow().filterNotNull()

    private val _route = MutableStateFlow<Route?>(null)
    override val route: Flow<Route> = _route.asStateFlow().filterNotNull()

    override fun setSignInAsStartDestination() {
        _startDestination.value = R.id.signInFragment
    }

    override fun setTabsAsStartDestination() {
        _startDestination.value = R.id.tabsFragment
    }

    override fun onStartDestinationSet() {
        _startDestination.value = null
    }

    override fun onRouteExecuted() {
        _route.value = null
    }

    override fun goToTabs() {
        _route.value = { navController -> navController.navigate(R.id.action_signInFragment_to_tabsFragment) }
    }

//    override fun goToAccountsFilter() {
//        _route.value = { navController -> navController.navigate(R.id.action_tabsFragment_to_accountFilterFragment) }
//    }
//
//    override fun goToPeopleList() {
//        _route.value = { navController -> navController.navigate(R.id.action_tabsFragment_to_peopleListFragment) }
//    }
//
//    override fun goToPeopleFilters() {
//        _route.value = { navController -> navController.navigate(R.id.action_tabsFragment_to_peopleFiltersFragment) }
//    }
//
//    override fun goToPositionsFilter() {
//        _route.value = { navController -> navController.navigate(R.id.action_tabsFragment_to_positionsFilterFragment) }
//    }
//
//    override fun goToProjectsFilter() {
//        _route.value = { navController -> navController.navigate(R.id.action_tabsFragment_to_projectsFilterFragment) }
//    }
//
//    override fun goToTabs() {
//        _route.value = { navController -> navController.navigate(R.id.action_signInFragment_to_tabsFragment) }
//    }
//
//    override fun closePeopleFilters() {
//        _route.value = { navController -> navController.navigate(R.id.action_close_peopleFilterFragment) }
//    }

}
