package com.example.android4.presentation.detailcourse.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.android4.core.designsystem.component.UrlImage
import com.example.android4.core.designsystem.theme.OnnaTheme
import com.example.android4.presentation.detailcourse.model.DetailCourseModel

@Composable
fun RecommendCourse(
    index: Int,
    detailCourse: DetailCourseModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "코스 ${index + 1}",
                    style = OnnaTheme.typography.title3b15,
                    color = OnnaTheme.colors.blue
                )
                Spacer(Modifier.width(12.dp))
                Text(
                    text = detailCourse.name,
                    style = OnnaTheme.typography.title1b17,
                    color = OnnaTheme.colors.black
                )
            }
            Text(
                text = detailCourse.address,
                style = OnnaTheme.typography.body7r13,
                color = OnnaTheme.colors.gray5
            )
            Spacer(Modifier.height(12.dp))
            ExpandableText(
                text = detailCourse.description,
                minCollapsedLines = 3
            )
        }
        Spacer(Modifier.height(20.dp))
        LazyRow(
            contentPadding = PaddingValues(start = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(detailCourse.imageUrls) { imageUrl ->
                UrlImage(
                    imageUrl = imageUrl,
                    shape = RectangleShape,
                    modifier = Modifier.size(136.dp)
                )
            }
        }
    }
}
