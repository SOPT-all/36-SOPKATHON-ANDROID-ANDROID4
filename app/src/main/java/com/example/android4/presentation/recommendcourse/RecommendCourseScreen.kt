package com.example.android4.presentation.recommendcourse

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.android4.R
import com.example.android4.core.designsystem.theme.OnnaTheme
import com.example.android4.core.util.noRippleClickable
import com.example.android4.presentation.detailcourse.DetailCourse
import com.example.android4.presentation.recommendcourse.component.CourseCard
import com.example.android4.presentation.recommendcourse.component.UserCard

@Composable
fun RecommendCourseScreen(
    paddingValues: PaddingValues,
    onBackClick: () -> Unit,
    onClick: (DetailCourse) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RecommendCourseViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(OnnaTheme.colors.white)
            .padding(paddingValues)
    ) {
        item {
            CourseTopBar(
                modifier = modifier,
                onBackClick = onBackClick,
                curatorName = uiState.curatorNickname
            )
            Spacer(modifier = modifier.height(28.dp))
        }

        item {
            UserCard(
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                curatorName = uiState.curatorNickname,
                curatorInfo = uiState.curatorDescription,
                userImage = uiState.profileImageUrl
            )
        }

        item {
            uiState.courses.forEach { course ->
                CourseCard(
                    courseDetail = course.description,
                    courseTitle = course.courseTitle,
                    postDay = course.recordDate,
                    onItemClick = { onClick(
                        DetailCourse(
                            courseId = course.courseId,
                            courseDescription = course.description,
                            isBookmarked = course.isBookmarked,
                            recordDate = course.recordDate,
                        )
                    ) },
                    imageUrl = course.imageUrls,
                    isBookmarked = course.isBookmarked,
                    onBookClick = { viewModel.toggleBookmark(course.courseId) }
                )
            }
        }
    }
}

@Composable
private fun CourseTopBar(
    modifier: Modifier,
    onBackClick: () -> Unit,
    curatorName: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_delete_24),
            contentDescription = null,
            modifier = modifier
                .padding(start = 20.dp)
                .size(24.dp)
                .noRippleClickable(
                    onClick = onBackClick
                )
        )

        Text(
            text = "${curatorName}이 추천하는 코스",
            style = OnnaTheme.typography.title1b17,
            color = OnnaTheme.colors.black
        )

        Spacer(Modifier.width(24.dp))
    }
}
