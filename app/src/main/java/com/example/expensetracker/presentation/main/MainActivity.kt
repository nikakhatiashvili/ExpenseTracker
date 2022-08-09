package com.example.expensetracker.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.expensetracker.R
import com.example.expensetracker.StartDestinationAndRouteProvider
import com.example.expensetracker.databinding.ActivityMainBinding
import com.example.expensetracker.navigate
import com.example.expensetracker.presentation.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    @Inject
    lateinit var routeProvider: StartDestinationAndRouteProvider

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mainViewModel.onAppLaunched()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        collectFlow(routeProvider.startDestination) { resId ->
            val navGraph = navController.navInflater.inflate(R.navigation.main_navigation)
            navGraph.setStartDestination(resId)
            navController.graph = navGraph
            routeProvider.onStartDestinationSet()
        }

        collectFlow(routeProvider.route) { route ->
            navController.navigate(route)
            routeProvider.onRouteExecuted()
        }
    }
}

private fun <T> AppCompatActivity.collectFlow(flow: Flow<T>, onCollect: suspend (T) -> Unit) =
    lifecycleScope.launch {
        flow.flowWithLifecycle(lifecycle, Lifecycle.State.CREATED).collectLatest(onCollect)
    }
