package com.example.expensetracker.presentation.tabs.di

import com.example.expensetracker.RouteProvider
import com.example.expensetracker.presentation.detail.DetailRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn

@InstallIn(TabsComponent::class)
@Module
interface TabsRouterModule {

    @Binds
    fun bindRouteProvider(impl: TabsRouterImpl): RouteProvider

    @Binds
    fun bindHomeRouter(impl: TabsRouterImpl): DetailRouter

//    @Binds
//    fun bindPositionsTabsRouter(impl: TabsRouterImpl): PositionsTabRouter
//
//    @Binds
//    fun bindProjectsTabsRouter(impl: TabsRouterImpl): ProjectsTabRouter
//
//    @Binds
//    fun bindAccountsTabsRouter(impl: TabsRouterImpl): AccountsTabRouter
}
