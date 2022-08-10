package com.example.expensetracker.di

import com.example.expensetracker.StartDestinationAndRouteProvider
import com.example.expensetracker.presentation.main.MainRouterImpl
import com.example.expensetracker.presentation.main.StartDestinationMainRouter
import com.example.expensetracker.presentation.signin.SignInMainRouter
import com.example.expensetracker.presentation.signup.SignUpMainRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface MainRouterModule {

    @Binds
    fun bindSignInMainRouter(impl: MainRouterImpl): SignInMainRouter

    @Binds
    fun bindSignUpMainRouter(impl: MainRouterImpl): SignUpMainRouter

    @Binds
    fun bindStartDestinationMainRouter(impl: MainRouterImpl): StartDestinationMainRouter

    @Binds
    fun bindStartDestinationAndRouteProvider(impl: MainRouterImpl): StartDestinationAndRouteProvider

}