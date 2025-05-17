package com.example.android4.presentation.landing

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.android4.core.navigation.Route
import kotlinx.serialization.Serializable

fun NavController.navigateToLanding(
    navOptions: NavOptions? = null
) {
    navigate(Landing, navOptions)
}

fun NavGraphBuilder.landingNavGraph(
    navigateToHome: () -> Unit
) {
    composable<Landing> {
        LandingScreen(
            onClick = navigateToHome
        )
    }
}

@Serializable
data object Landing : Route
