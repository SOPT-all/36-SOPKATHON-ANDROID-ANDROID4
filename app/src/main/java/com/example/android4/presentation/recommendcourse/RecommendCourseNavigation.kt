package com.example.android4.presentation.recommendcourse

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.android4.core.navigation.Route
import com.example.android4.presentation.detailcourse.DetailCourse
import kotlinx.serialization.Serializable

fun NavController.navigateToRecommendCourse(
    userId: Int,
    navOptions: NavOptions? = null
) {
    navigate(RecommendCourse(userId), navOptions)
}

fun NavGraphBuilder.recommendCourseNavGraph(
    paddingValues: PaddingValues,
    navigateBack: () -> Unit,
    navigateToDetailCourse: (DetailCourse) -> Unit
) {
    composable<RecommendCourse> {
        RecommendCourseScreen(
            paddingValues = paddingValues,
            onBackClick = navigateBack,
            onClick = navigateToDetailCourse
        )
    }
}

@Serializable
data class RecommendCourse(
    val userId: Int
) : Route
