package com.example.expensetracker.presentation.tabs.di

import com.example.expensetracker.RouteProvider
import com.example.expensetracker.presentation.detail.DetailRouter
import dagger.hilt.DefineComponent
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Scope
import javax.inject.Singleton

@Singleton
class TabsComponentHolder @Inject constructor(private val builder: TabsComponent.Builder) {

    private var component: TabsComponent? = null

    fun create() {
        component = builder.build()
    }

    fun destroy() {
        component = null
    }

    fun getRouteProvider() = entryPoint()?.routeProvider()

    fun getDetailTabsRouter() = entryPoint()?.detailTabsRouter()

//    fun getPositionsTabRouter() = entryPoint()?.positionsTabRouter()
//
//    fun getProjectsTabRouter() = entryPoint()?.projectsTabRouter()
//
//    fun getAccountsTabRouter() = entryPoint()?.accountsTabRouter()

    private fun entryPoint() = component?.let { EntryPoints.get(it, TabsEntryPoint::class.java) }
}

@Scope
@Retention
annotation class TabsScope

@DefineComponent(parent = SingletonComponent::class)
@TabsScope
interface TabsComponent {

    @DefineComponent.Builder
    interface Builder {
        fun build(): TabsComponent
    }
}

@EntryPoint
@InstallIn(TabsComponent::class)
interface TabsEntryPoint {

    fun detailTabsRouter(): DetailRouter

    fun routeProvider(): RouteProvider

//    fun positionsTabRouter(): PositionsTabRouter
//
//    fun projectsTabRouter(): ProjectsTabRouter
//
//    fun accountsTabRouter(): AccountsTabRouter
}
