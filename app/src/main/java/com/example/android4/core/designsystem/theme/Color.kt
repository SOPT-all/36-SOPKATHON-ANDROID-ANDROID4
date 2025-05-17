package com.example.android4.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Blue = Color(0xFF3679FF)
val LightBlue = Color(0xFFF3F5FF)
val White = Color(0xFFFFFFFF)
val Gray1 = Color(0xFFEAEAEA)
val Gray2 = Color(0xFFECECEC)
val Gray3 = Color(0xFFBCBCBC)
val Gray4 = Color(0xFFAAAAAA)
val Gray5 = Color(0xFF8F8F8F)
val Gray6 = Color(0xFF666666)
val Gray7 = Color(0xFF383838)
val Black = Color(0xFF121212)

@Immutable
data class OnnaColors(
    val blue: Color,
    val lightBlue: Color,
    val white: Color,
    val gray1: Color,
    val gray2: Color,
    val gray3: Color,
    val gray4: Color,
    val gray5: Color,
    val gray6: Color,
    val gray7: Color,
    val black: Color,
)

val defaultOnnaColors = OnnaColors(
    blue = Blue,
    lightBlue = LightBlue,
    white = White,
    gray1 = Gray1,
    gray2 = Gray2,
    gray3 = Gray3,
    gray4 = Gray4,
    gray5 = Gray5,
    gray6 = Gray6,
    gray7 = Gray7,
    black = Black,
)

val LocalOnnaColorsProvider = staticCompositionLocalOf { defaultOnnaColors }