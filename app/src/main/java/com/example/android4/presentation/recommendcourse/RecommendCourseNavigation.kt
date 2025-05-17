package com.example.android4.presentation.recommendcourse

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.android4.core.navigation.Route
import kotlinx.serialization.Serializable

fun NavController.navigateToRecommendCourse(
    navOptions: NavOptions? = null
) {
    navigate(RecommendCourse, navOptions)
}

fun NavGraphBuilder.recommendCourseNavGraph(
    paddingValues: PaddingValues
) {
    composable<RecommendCourse> {
        RecommendCourseScreen(
            paddingValues = paddingValues
        )
    }
}

@Serializable
data object RecommendCourse : Route
