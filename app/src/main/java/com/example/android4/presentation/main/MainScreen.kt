package com.example.android4.presentation.main

import android.app.Activity
import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import com.example.android4.core.designsystem.component.OnnaSnackbar
import com.example.android4.core.designsystem.event.LocalSnackBarTrigger
import com.example.android4.presentation.detailcourse.detailCourseNavGraph
import com.example.android4.presentation.home.homeNavGraph
import com.example.android4.presentation.landing.landingNavGraph
import com.example.android4.presentation.main.component.MainBottomBar
import com.example.android4.presentation.mypage.myPageNavGraph
import com.example.android4.presentation.recommendcourse.recommendCourseNavGraph
import com.example.android4.presentation.splash.splashNavGraph
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val SHOW_SNACKBAR_TIMEMILLIS = 3000L

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator()
) {
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    val onShowSnackBar: (String) -> Unit = { message ->
        coroutineScope.launch {
            snackBarHostState.currentSnackbarData?.dismiss()
            val job = launch {
                snackBarHostState.showSnackbar(message)
            }
            delay(SHOW_SNACKBAR_TIMEMILLIS)
            job.cancel()
        }
    }

    OnnaBackHandler(
        context = context,
        onShowSnackbar = {
            onShowSnackBar("한번 더 누르면 앱이 종료돼요!")
        }
    )

    CompositionLocalProvider(
        LocalSnackBarTrigger provides onShowSnackBar
    ) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState) { snackbarData ->
                    OnnaSnackbar(text = snackbarData.visuals.message)
                }
            },
            bottomBar = {
                MainBottomBar(
                    visible = navigator.shouldShowBottomBar(),
                    tabs = MainTab.entries.toPersistentList(),
                    currentTab = navigator.currentTab,
                    onTabSelected = navigator::navigate
                )
            },
            modifier = Modifier
                .fillMaxSize()
        ) { paddingValues ->
            NavHost(
                enterTransition = {
                    EnterTransition.None
                },
                exitTransition = {
                    ExitTransition.None
                },
                popEnterTransition = {
                    EnterTransition.None
                },
                popExitTransition = {
                    ExitTransition.None
                },
                navController = navigator.navController,
                startDestination = navigator.startDestination
            ) {
                splashNavGraph(
                    navigateToLanding = navigator::navigateToLanding
                )

                landingNavGraph(
                    navigateToHome = navigator::navigateToHome
                )
                homeNavGraph(
                    paddingValues = paddingValues,
                    navigateToRecommendCourse = navigator::navigateToRecommendCourse
                )
                recommendCourseNavGraph(
                    paddingValues = paddingValues,
                    navigateToDetailCourse = navigator::navigateToDetailCourse
                )
                detailCourseNavGraph(
                    paddingValues = paddingValues
                )
                myPageNavGraph(
                    paddingValues = paddingValues
                )
            }
        }
    }
}

@Composable
fun OnnaBackHandler(
    context: Context,
    enabled: Boolean = true,
    exitDelayMillis: Long = 3000L,
    onShowSnackbar: () -> Unit = {}
) {
    var backPressedTime by remember {
        mutableLongStateOf(0L)
    }

    BackHandler(enabled = enabled) {
        if (System.currentTimeMillis() - backPressedTime <= exitDelayMillis) {
            (context as Activity).finish()
        } else {
            onShowSnackbar()
        }
        backPressedTime = System.currentTimeMillis()
    }
}
