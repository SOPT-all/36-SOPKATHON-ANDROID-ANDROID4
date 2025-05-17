package com.example.android4.presentation.main

import androidx.compose.runtime.Composable
import com.example.android4.R
import com.example.android4.core.navigation.MainTabRoute
import com.example.android4.core.navigation.Route
import com.example.android4.presentation.home.Home
import com.example.android4.presentation.mypage.MyPage

enum class MainTab(
    val selectedIconResource: Int,
    val unselectedIconResource: Int,
    val label: String,
    val route: MainTabRoute
) {
    HOME(
        selectedIconResource = R.drawable.icon_map_mono_24,
        unselectedIconResource = R.drawable.icon_map_mono_grey3_24,
        label = "큐레이션",
        route = Home
    ),
    MYPAGE(
        selectedIconResource = R.drawable.icon_user_mono_24,
        unselectedIconResource = R.drawable.icon_user_mono_grey3_24,
        label = "마이페이지",
        route = MyPage
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}
