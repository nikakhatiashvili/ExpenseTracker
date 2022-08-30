package com.example.expensetracker.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.expensetracker.R
import com.example.expensetracker.StartDestinationAndRouteProvider
import com.example.expensetracker.databinding.ActivityMainBinding
import com.example.expensetracker.navigate
import com.example.expensetracker.presentation.common.viewBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var  navView: NavigationView

    private val binding by viewBinding(ActivityMainBinding::inflate)

    @Inject
    lateinit var routeProvider: StartDestinationAndRouteProvider

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mainViewModel.onAppLaunched()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val navController = navHostFragment.navController


        setSupportActionBar(binding.toolbar)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        navView = binding.navView
        val sideNavController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.exchangeRatesFragment, R.id.signInFragment, R.id.signUpFragment,R.id.tabsFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(sideNavController, appBarConfiguration)
        navView.setupWithNavController(sideNavController)


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
        navView.menu.findItem(R.id.tabsFragment).isVisible = false
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    fun hideLogin(){
        navView.menu.findItem(R.id.signInFragment).isVisible = false
    }
    fun showHome(){
        navView.menu.findItem(R.id.tabsFragment).isVisible = true
    }
}

private fun <T> AppCompatActivity.collectFlow(flow: Flow<T>, onCollect: suspend (T) -> Unit) =
    lifecycleScope.launch {
        flow.flowWithLifecycle(lifecycle, Lifecycle.State.CREATED).collectLatest(onCollect)
    }
