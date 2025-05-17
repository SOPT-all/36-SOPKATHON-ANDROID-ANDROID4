package com.example.android4.presentation.detailcourse.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.android4.core.designsystem.theme.OnnaTheme

val ExpandableTextColor: Color @Composable get() = OnnaTheme.colors.gray7
val SeeMoreAndLessColor: Color @Composable get() = OnnaTheme.colors.gray3

/** 접혀있을 때 라인 수*/

/**
 * @param minCollapsedLines 접혔을 때 라인 수
 */
@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    text: String,
    minCollapsedLines: Int = 1
) {
    // @formatter:off
    var isExpanded by remember { mutableStateOf(false) }
    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }
    var isClickable by remember { mutableStateOf(false) }

    var expendableTextStyle = OnnaTheme.typography.body3r15.toSpanStyle().copy(
        color = ExpandableTextColor
    )
    val seeMoreAndLessTextStyle = OnnaTheme.typography.body7r13.toSpanStyle().copy(
        color = SeeMoreAndLessColor
    )
    // 내용을 초기에 설정한 text 생성
    var textWithMoreLess by remember {
        mutableStateOf(
            buildAnnotatedString {
                withStyle(style = expendableTextStyle) {
                    append(text)
                }
            }
        )
    }

    LaunchedEffect(textLayoutResult) {
        textLayoutResult?.let {
            when {
                // 텍스트 확장 상태
                isExpanded -> {
                    textWithMoreLess = originString(
                        text,
                        expendableTextStyle,
                        seeMoreAndLessTextStyle
                    )
                }

                // 텍스트가 펼쳐지지 않은 상태이고 최대 줄 수를 초과하는 경우
                !isExpanded && it.hasVisualOverflow -> {
                    val lastCharIndex = it.getLineEnd(minCollapsedLines - 1)
                    textWithMoreLess = summarizedString(
                        text = text,
                        lastCharIndex = lastCharIndex,
                        expandableTextStyle = expendableTextStyle,
                        seeMoreAndLessTextStyle = seeMoreAndLessTextStyle
                    )
                    isClickable = true
                }
            }
        }
    }

    // UriHandler parse and opens URI inside AnnotatedString Item in Browse
    val uriHandler = LocalUriHandler.current

    // Composable container
    Box(modifier = modifier) {
        SelectionContainer {
            ClickableText(
                text = textWithMoreLess,
                style = TextStyle(color = Color.DarkGray, fontSize = 15.sp),
                onClick = { offset ->
                    textWithMoreLess.getStringAnnotations(
                        tag = "link_tag",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let { stringAnnotation ->
                        uriHandler.openUri(stringAnnotation.item)
                    }

                    if (isClickable) {
                        textWithMoreLess.getStringAnnotations(
                            tag = "show_more_tag",
                            start = offset,
                            end = offset
                        ).firstOrNull()?.let {
                            isExpanded = !isExpanded
                        }
                    }
                },
                maxLines = if (isExpanded) Int.MAX_VALUE else minCollapsedLines,
                onTextLayout = { textLayoutResult = it },
                modifier = modifier.animateContentSize()
            )
        }
    }
    // @formatter:on
}

fun originString(
    text: String,
    expandableTextStyle: SpanStyle,
    seeMoreAndLessTextStyle: SpanStyle
): AnnotatedString {
    return buildAnnotatedString {
        // 내용 추가
        withStyle(style = expandableTextStyle) {
            append(text)
        }
        pushStringAnnotation(tag = "show_more_tag", annotation = "") // 어노테이션 추가
        withStyle(style = seeMoreAndLessTextStyle) {
            append(" 접기")
        }
        pop() // 어노테이션 제거
    }
}

fun summarizedString(
    text: String,
    lastCharIndex: Int,
    showMoreString: String = "... 더보기",
    expandableTextStyle: SpanStyle,
    seeMoreAndLessTextStyle: SpanStyle
): AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = expandableTextStyle) {
            // 내용 추가
            append(
                text.substring(0, if (lastCharIndex > text.length) text.length else lastCharIndex)
                    .dropLast(showMoreString.length + 1) // ... more 추가를 위에 문장 자르기
                    .dropLastWhile { it == ' ' || it == '.' }
            ) // 주의: 조정한 글자가 오버플로우되면 무한 루프 발생
        }
        append("... ")
        pushStringAnnotation(tag = "show_more_tag", annotation = "")
        withStyle(style = seeMoreAndLessTextStyle) {
            append("더보기")
        }
        pop()
    }
}
