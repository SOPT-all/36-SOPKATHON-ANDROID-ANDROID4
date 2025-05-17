package com.example.android4.presentation.recommendcourse

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import com.example.android4.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.android4.core.designsystem.theme.OnnaTheme
import com.example.android4.presentation.recommendcourse.component.CourseCard
import com.example.android4.presentation.recommendcourse.component.UserCard

@Composable
fun RecommendCourseScreen(
    paddingValues: PaddingValues,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {


    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        item {
            CourseTopBar(
                modifier = modifier
            )
            Spacer(modifier = modifier.height(28.dp))
        }

        item {

            UserCard(
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                curatorName = "김뚝딱",
                curatorInfo = "큐레이터에 대한 설명 \n경남 지역에서 25년째 살고있는 어쩌구")

        }

        item {

            CourseCard(
                courseDetail = "코스에 대한 상세 설명 코스에 대한 상세 설명코스에 대한 상세 설명코스에 대한 상세 설명코스에 대한 상세 설명 코스에 대한 상세 설명코스에 대한 상세 설명코스에 대한 상세 설명",
                postDay = "2025.01.23")

            CourseCard(
                courseDetail = "안녕",
                postDay = "2025.01.23")

            CourseCard(
                courseDetail = "안ㅇㄹ",
                postDay = "2025.01.23")

        }










    }
}

@Composable
private fun CourseTopBar(modifier: Modifier) {

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_delete_24),
            contentDescription = null,
            modifier = modifier
                .padding(start = 20.dp)
                .size(24.dp)
        )


        Text(
            //text = "{$curatorName}이 추천하는 코스"
            text = "김뚝딱이 추천하는 코스",
            style = OnnaTheme.typography.title1b17,
            color = OnnaTheme.colors.black
        )

        Spacer(Modifier.width(24.dp))
    }

}
