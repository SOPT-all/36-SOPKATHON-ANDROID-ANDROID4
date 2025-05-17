package com.example.android4.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.android4.R
import com.example.android4.core.designsystem.component.UrlImage
import com.example.android4.core.designsystem.theme.OnnaTheme
import com.example.android4.core.util.dropShadow
import kotlin.math.absoluteValue
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val pagerState = rememberPagerState(initialPage = 0) { uiState.cardList.size }
    val coroutineScope = rememberCoroutineScope()
    val screenWidth = with(LocalDensity.current) {
        LocalWindowInfo.current.containerSize.width.toDp()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(OnnaTheme.colors.white)
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(38.dp))

        Text(
            text = "경남을 누구보다 잘 알고있는\n" +
                "큐레이터들을 소개합니다.",
            style = OnnaTheme.typography.title4b22,
            color = OnnaTheme.colors.black,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(23.dp))

        if (uiState.cardList.isNotEmpty()) {
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = screenWidth * 0.15f),
                modifier = Modifier.fillMaxWidth()
            ) { page ->
                val card = uiState.cardList[page]
                HomeCard(
                    imageUrl = card.imageUrl,
                    userName = card.userName,
                    userDescription = card.userDescription,
                    onButtonClick = { onClick(card.id) },
                    modifier = Modifier
                        .graphicsLayer {
                            val pageOffset = (
                                (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                                ).absoluteValue

                            val scale = 0.9f + 0.1f * (1f - pageOffset.coerceIn(0f, 1f))
                            scaleX = scale
                            scaleY = scale

                            alpha = 0.4f + 0.6f * (1f - pageOffset.coerceIn(0f, 1f))
                        }
                        .padding(8.dp)
                        .dropShadow(
                            shape = RoundedCornerShape(16.dp),
                            offsetY = 4.dp,
                            blur = 10.dp,
                            color = OnnaTheme.colors.black.copy(alpha = 0.1f)
                        )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 51.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_left_white_24),
                    contentDescription = null,
                    tint = OnnaTheme.colors.blue,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(OnnaTheme.colors.lightBlue)
                        .clickable(onClick = {
                            if (pagerState.currentPage > 0) {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                    viewModel.updateCurrentPage(pagerState.currentPage - 1)
                                }
                            }
                        })
                        .padding(14.dp)
                )

                Spacer(modifier = Modifier.width(40.dp))

                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_left_white_24),
                    contentDescription = null,
                    tint = OnnaTheme.colors.blue,
                    modifier = Modifier
                        .rotate(180f)
                        .clip(CircleShape)
                        .background(OnnaTheme.colors.lightBlue)
                        .clickable(onClick = {
                            if (pagerState.currentPage < uiState.cardList.size - 1) {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                    viewModel.updateCurrentPage(pagerState.currentPage + 1)
                                }
                            }
                        })
                        .padding(14.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(46.dp))
    }
}

@Composable
private fun HomeCard(
    imageUrl: String,
    userName: String,
    userDescription: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .background(OnnaTheme.colors.white)
            .padding(16.dp)
    ) {
        UrlImage(
            imageUrl = imageUrl,
            shape = RectangleShape,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(288 / 205f)
                .padding(bottom = 12.dp)
        )
        Text(
            text = userName,
            style = OnnaTheme.typography.title4b22,
            color = OnnaTheme.colors.black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = 7.dp)
        )
        Text(
            text = userDescription,
            style = OnnaTheme.typography.body8r16,
            color = OnnaTheme.colors.gray6,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.height(118.dp)
        )

        HomeButton(
            onButtonClick = onButtonClick,
            modifier = Modifier.padding(top = 15.dp)
        )
    }
}

@Composable
private fun HomeButton(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = "대표 추천 코스 구경",
        style = OnnaTheme.typography.title5b18,
        color = OnnaTheme.colors.white,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center,
        modifier = modifier
            .clip(RoundedCornerShape(13.dp))
            .background(OnnaTheme.colors.blue)
            .clickable(onClick = onButtonClick)
            .fillMaxWidth()
            .padding(vertical = 15.dp)
    )
}
