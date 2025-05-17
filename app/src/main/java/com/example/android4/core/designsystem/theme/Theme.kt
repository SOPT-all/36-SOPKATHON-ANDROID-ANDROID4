package com.example.android4.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object OnnaTheme {
    val colors: OnnaColors
        @Composable
        @ReadOnlyComposable
        get() = LocalOnnaColorsProvider.current
    val typography: OnnaTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalOnnaTypographyProvider.current
}

@Composable
fun ProvideOnnaColorsAndTypography(
    colors: OnnaColors,
    typography: OnnaTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalOnnaColorsProvider provides colors,
        LocalOnnaTypographyProvider provides typography,
        content = content
    )
}

@Composable
fun OnnaTheme(
    content: @Composable () -> Unit
) {
    ProvideOnnaColorsAndTypography(
        colors = defaultOnnaColors,
        typography = defaultOnnaTypography
    ) {
        MaterialTheme(
            content = content
        )
    }
}
