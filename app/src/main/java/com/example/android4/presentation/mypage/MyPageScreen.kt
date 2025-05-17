package com.example.android4.presentation.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android4.core.designsystem.component.UrlImage
import com.example.android4.core.designsystem.theme.BaseLine
import com.example.android4.core.designsystem.theme.Black
import com.example.android4.core.designsystem.theme.Gray6
import com.example.android4.core.designsystem.theme.Gray7
import com.example.android4.core.designsystem.theme.OnnaTheme
import com.example.android4.core.designsystem.theme.White
import com.example.android4.data.model.Course
import com.example.android4.data.model.UserProfile


@Composable
fun MyPageScreen(paddingValues: PaddingValues, viewModel: MyPageViewModel = hiltViewModel()) {

    val userProfile by viewModel.userProfile.collectAsState()
    val courses by viewModel.courses.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchMyPage(userId = 531)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OnnaTheme.colors.white)

    ) {
        MypageHeader()
        Profile(userProfile = userProfile)
        Spacer(modifier = Modifier.height(27.dp))

        MyCourse(courses = courses)
    }
}


@Composable
fun MypageHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "마이페이지",
            style = OnnaTheme.typography.title1b17,
            color = Black
        )
    }
}

@Composable
fun Profile(userProfile: UserProfile) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .height(80.dp)
            .background(color = BaseLine, shape = RoundedCornerShape(12.dp)),
    ) {
        UrlImage(
            imageUrl = userProfile.imageUrl,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(13.dp))
        Text(
            text = userProfile.name,
            color = Gray7,
            style = OnnaTheme.typography.title3b15
        )
    }
}


@Composable
fun MyCourse(courses: List<Course>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 20.dp),
        //  verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            Text(
                text = "내가 저장한 코스",
                color = Black,
                style = OnnaTheme.typography.title5b18
            )

        }

        items(courses) { course ->
            CourseItem(course)

        }
    }
}

@Composable
fun CourseItem(course: Course) {
    Box(
        modifier = Modifier
            .padding(vertical = 15.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(10.dp),
                clip = false
            )

            .clip(RoundedCornerShape(10.dp))
            .background(White)
            .padding(start = 20.dp, top = 15.dp, bottom = 15.dp, end = 20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = course.title,
                style = OnnaTheme.typography.title3b15,
                color = Black
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = course.description,
                style = OnnaTheme.typography.body2m15,
                color = Gray6
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.fillMaxWidth()

            ) {
                course.imageUrls.take(2).forEach { url ->
                    UrlImage(
                        imageUrl = url,
                        modifier = Modifier.weight(0.5f).aspectRatio(140/105f),
                        shape = RectangleShape,
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyPageScreenPreview() {
    MyPageScreen(paddingValues = PaddingValues(0.dp))
}
