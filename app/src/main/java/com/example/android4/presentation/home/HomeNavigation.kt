package com.example.android4.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.android4.core.navigation.MainTabRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) {
    navigate(Home, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    paddingValues: PaddingValues
) {
    composable<Home> {
        HomeScreen(
            paddingValues = paddingValues
        )
    }
}

@Serializable
data object Home : MainTabRoute
