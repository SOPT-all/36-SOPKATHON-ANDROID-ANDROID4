package com.example.android4.presentation.detailcourse

import androidx.lifecycle.ViewModel
import com.example.android4.presentation.detailcourse.model.DetailCourse
import com.example.android4.presentation.detailcourse.model.DetailCourseState

class DetailCourseViewModel: ViewModel() {
    val detailCourseDummy = DetailCourseState(
        isLike = false,
        courseName = "연휴 마지막 날\n들리기 좋은 통영 여행코스",
        courseDescription = "연휴를 마무리하기 좋은 통영의 감각적인 \n" + "공간들을 소개해 드릴게요!",
        date = "2025.01.02",
        courseList = listOf<DetailCourse>(
            DetailCourse(
                name = "진주 촉석루",
                address = "남강로 626 (본성동), 진주성 내",
                description = "미국 CNN에서 한국 방문시 꼭 가봐야 할 곳 50선에 선정된 촉석루는 남강변 벼랑 위에 우아하고 위엄 있게 서있는 우리나라 3대 누각 중 하나로 고려 고종 28년(1241)에 창건하여 8차례에 걸쳐 중수하였다.",
                imageUrls = listOf(
                    "https://avatars.githubusercontent.com/u/101113025?v=4",
                    "https://avatars.githubusercontent.com/u/101113025?v=4"
                )
            ),
            DetailCourse(
                name = "진주 촉석루",
                address = "남강로 626 (본성동), 진주성 내",
                description = "미국 CNN에서 한국 방문시 꼭 가봐야 할 곳 50선에 선정된 촉석루는 남강변 벼랑 위에 우아하고 위엄 있게 서있는 우리나라 3대 누각 중 하나로 고려 고종 28년(1241)에 창건하여 8차례에 걸쳐 중수하였다.",
                imageUrls = listOf(
                    "https://avatars.githubusercontent.com/u/101113025?v=4",
                    "https://avatars.githubusercontent.com/u/101113025?v=4"
                )
            ),
            DetailCourse(
                name = "진주 촉석루",
                address = "남강로 626 (본성동), 진주성 내",
                description = "미국 CNN에서 한국 방문시 꼭 가봐야 할 곳 50선에 선정된 촉석루는 남강변 벼랑 위에 우아하고 위엄 있게 서있는 우리나라 3대 누각 중 하나로 고려 고종 28년(1241)에 창건하여 8차례에 걸쳐 중수하였다.",
                imageUrls = listOf(
                    "https://avatars.githubusercontent.com/u/101113025?v=4",
                    "https://avatars.githubusercontent.com/u/101113025?v=4"
                )
            ),
        )
    )

    fun toggleBookmark() {
        //TODO: 저장 여부 변경
    }
}