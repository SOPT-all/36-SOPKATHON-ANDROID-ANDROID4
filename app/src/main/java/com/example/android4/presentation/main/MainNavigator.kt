package com.example.android4.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.android4.presentation.detailcourse.DetailCourse
import com.example.android4.presentation.detailcourse.navigateToDetailCourse
import com.example.android4.presentation.home.navigateToHome
import com.example.android4.presentation.landing.navigateToLanding
import com.example.android4.presentation.mypage.navigateToMyPage
import com.example.android4.presentation.recommendcourse.navigateToRecommendCourse
import com.example.android4.presentation.splash.Splash

const val NAVIGATION_ROOT = 0

class MainNavigator(
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = Splash

    val currentTab: MainTab?
        @Composable get() = MainTab.Companion.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            navController.currentDestination?.route?.let {
                popUpTo(it) {
                    inclusive = true
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.HOME -> navController.navigateToHome(navOptions)
            MainTab.MYPAGE -> navController.navigateToMyPage(navOptions)
        }
    }

    @Composable
    fun shouldShowBottomBar() = MainTab.Companion.contains {
        currentDestination?.hasRoute(it::class) == true
    }

    fun navigateToHome(){
        val navOptions = navOptions {
            navController.currentDestination?.route?.let {
                popUpTo(it) {
                    inclusive = true
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
        navController.navigateToHome(navOptions)
    }

    fun navigateToLanding() {
        val navOptions = navOptions {
            navController.currentDestination?.route?.let {
                popUpTo(it) {
                    inclusive = true
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
        navController.navigateToLanding(navOptions)
    }

    fun navigateToRecommendCourse(
        userId: Int,
        navOptions: NavOptions? = null
    ) {
        navController.navigateToRecommendCourse(navOptions = navOptions, userId = userId)
    }

    fun navigateToDetailCourse(
        detailCourse: DetailCourse,
        navOptions: NavOptions? = null
    ) {
        navController.navigateToDetailCourse(detailCourse, navOptions)
    }

    fun navigateUp() {
        navController.navigateUp()
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController()
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}
