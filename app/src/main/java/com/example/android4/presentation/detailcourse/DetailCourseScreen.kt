package com.example.android4.presentation.detailcourse

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.android4.R
import com.example.android4.core.designsystem.theme.Blue
import com.example.android4.core.designsystem.theme.OnnaTheme
import com.example.android4.core.util.noRippleClickable
import com.example.android4.presentation.detailcourse.component.RecommendCourse
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun DetailCourseScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    viewModel: DetailCourseViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = Blue
        )
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                .paint(
                    painter = painterResource(R.drawable.img_gradient_bg),
                    contentScale = ContentScale.FillWidth
                )
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 37.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Spacer(Modifier.height(12.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    Text(
                        text = uiState.value.data.courseName,
                        style = OnnaTheme.typography.title4b22,
                        color = OnnaTheme.colors.white,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.width(10.dp))
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            if (uiState.value.data.isLike) R.drawable.ic_bookmark_fill_24
                            else R.drawable.ic_bookmark_24
                        ),
                        contentDescription = null,
                        tint = OnnaTheme.colors.white,
                        modifier = Modifier
                            .size(24.dp)
                            .noRippleClickable(
                                onClick = viewModel::toggleBookmark
                            )
                    )
                }
                Spacer(Modifier.height(18.dp))
                Text(
                    text = uiState.value.data.courseDescription,
                    style = OnnaTheme.typography.body2m15,
                    color = OnnaTheme.colors.white
                )
                Spacer(Modifier.height(18.dp))
                Text(
                    text = uiState.value.data.date,
                    style = OnnaTheme.typography.body7r13,
                    color = OnnaTheme.colors.white
                )
            }
        }
        LazyColumn(
            contentPadding = PaddingValues(top = 30.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            itemsIndexed(uiState.value.data.courseList) { index, data ->
                RecommendCourse(index, data)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailCourseScreenPreview() {
    OnnaTheme {
        DetailCourseScreen(
            paddingValues = PaddingValues()
        )
    }
}
