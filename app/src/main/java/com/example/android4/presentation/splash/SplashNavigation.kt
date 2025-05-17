package com.example.android4.presentation.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.android4.core.navigation.Route
import kotlinx.serialization.Serializable

fun NavGraphBuilder.splashNavGraph(
    navigateToLanding: () -> Unit
) {
    composable<Splash> {
        SplashScreen(
            navigateToLanding = navigateToLanding
        )
    }
}

@Serializable
data object Splash : Route
