package com.example.android4.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.android4.R

val onnaBold = FontFamily(Font(R.font.pretendard_bold))
val onnaSemiBold = FontFamily(Font(R.font.pretendard_semibold))
val onnaMedium = FontFamily(Font(R.font.pretendard_medium))
val onnaRegular = FontFamily(Font(R.font.pretendard_regular))

@Immutable
data class OnnaTypography(
    // title
    val title1b17: TextStyle,
    val title2sb15: TextStyle,
    val title3b15: TextStyle,
    val title5b18: TextStyle,
    val title4b22: TextStyle,
    // body
    val body1sb15: TextStyle,
    val body2m15: TextStyle,
    val body3r15: TextStyle,
    val body4b13: TextStyle,
    val body5sb13: TextStyle,
    val body6m13: TextStyle,
    val body7r13: TextStyle,
    val body8r16: TextStyle,
    // label
    val label1b11: TextStyle,
    val label2m11: TextStyle,
    val label3r11: TextStyle,
    val label4m9: TextStyle
)

private fun OnnaTextStyle(
    fontFamily: FontFamily,
    fontSize: TextUnit,
    lineHeight: TextUnit = 1.4.em,
    letterSpacing: TextUnit = 0.em
): TextStyle = TextStyle(
    fontFamily = fontFamily,
    fontSize = fontSize,
    lineHeight = lineHeight,
    letterSpacing = letterSpacing,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)

val defaultOnnaTypography = OnnaTypography(
    title1b17 = OnnaTextStyle(
        fontFamily = onnaBold,
        fontSize = 17.sp
    ),
    title2sb15 = OnnaTextStyle(
        fontFamily = onnaSemiBold,
        fontSize = 17.sp
    ),
    title3b15 = OnnaTextStyle(
        fontFamily = onnaBold,
        fontSize = 15.sp
    ),
    title5b18 = OnnaTextStyle(
        fontFamily = onnaBold,
        fontSize = 18.sp
    ),
    title4b22 = OnnaTextStyle(
        fontFamily = onnaBold,
        fontSize = 22.sp,
        lineHeight = 1.3.em
    ),
    body1sb15 = OnnaTextStyle(
        fontFamily = onnaSemiBold,
        fontSize = 15.sp
    ),
    body2m15 = OnnaTextStyle(
        fontFamily = onnaMedium,
        fontSize = 15.sp
    ),
    body3r15 = OnnaTextStyle(
        fontFamily = onnaRegular,
        fontSize = 15.sp
    ),
    body4b13 = OnnaTextStyle(
        fontFamily = onnaBold,
        fontSize = 13.sp
    ),
    body5sb13 = OnnaTextStyle(
        fontFamily = onnaSemiBold,
        fontSize = 13.sp
    ),
    body6m13 = OnnaTextStyle(
        fontFamily = onnaMedium,
        fontSize = 13.sp
    ),
    body7r13 = OnnaTextStyle(
        fontFamily = onnaRegular,
        fontSize = 13.sp
    ),
    body8r16 = OnnaTextStyle(
        fontFamily = onnaRegular,
        fontSize = 16.sp
    ),
    label1b11 = OnnaTextStyle(
        fontFamily = onnaBold,
        fontSize = 11.sp
    ),
    label2m11 = OnnaTextStyle(
        fontFamily = onnaMedium,
        fontSize = 11.sp
    ),
    label3r11 = OnnaTextStyle(
        fontFamily = onnaRegular,
        fontSize = 11.sp
    ),
    label4m9 = OnnaTextStyle(
        fontFamily = onnaMedium,
        fontSize = 9.sp
    )
)

val LocalOnnaTypographyProvider = staticCompositionLocalOf { defaultOnnaTypography }
