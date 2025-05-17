package com.example.android4.presentation.detailcourse

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.android4.core.navigation.Route
import kotlinx.serialization.Serializable

fun NavController.navigateToDetailCourse(
    detailCourse: DetailCourse,
    navOptions: NavOptions? = null
) {
    navigate(detailCourse, navOptions)
}

fun NavGraphBuilder.detailCourseNavGraph(
    paddingValues: PaddingValues
) {
    composable<DetailCourse> {
        DetailCourseScreen(
            paddingValues = paddingValues
        )
    }
}

@Serializable
data class DetailCourse(
    val courseId: Int,
    val courseDescription: String,
    val isBookmarked: Boolean,
    val recordDate: String
) : Route
