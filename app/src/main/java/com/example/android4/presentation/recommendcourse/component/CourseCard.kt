package com.example.android4.presentation.recommendcourse.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android4.R
import com.example.android4.core.designsystem.component.UrlImage
import com.example.android4.core.designsystem.theme.OnnaTheme

@Composable
fun CourseCard(
    modifier: Modifier = Modifier,
    courseTitle: String,
    onBookClick: () -> Unit,
    imageUrl: List<String>,
    courseDetail: String,
    onItemClick: () -> Unit,
    postDay: String,
    isBookmarked: Boolean = false
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(OnnaTheme.colors.white)
            .padding(20.dp)
            .clickable(
                onClick = onItemClick
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = courseTitle,
                style = OnnaTheme.typography.title1b17,
                color = OnnaTheme.colors.black
            )

            Spacer(modifier = modifier.weight(1f))
            Icon(
                imageVector = ImageVector.vectorResource(
                    if (!isBookmarked) R.drawable.ic_bookmark_24 else R.drawable.ic_bookmark_fill_24
                ),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = modifier.clickable {
                    onBookClick()
                }
            )
        }

        Spacer(modifier = modifier.height(10.dp))

        Text(
            text = courseDetail,
            style = OnnaTheme.typography.body2m15,
            color = OnnaTheme.colors.gray5,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis

        )

        Spacer(modifier = modifier.height(17.dp))

        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            UrlImage(
                imageUrl = if (imageUrl.isNotEmpty()) imageUrl[0] else "",
                contentScale = ContentScale.Crop,
                shape = RectangleShape,
                modifier = modifier
                    .weight(1f)
                    .aspectRatio(151 / 113f)

            )

            UrlImage(
                imageUrl = if (imageUrl.isNotEmpty()) imageUrl[1] else "",
                contentScale = ContentScale.Crop,
                shape = RectangleShape,
                modifier = modifier
                    .weight(1f)
                    .aspectRatio(151 / 113f)

            )
        }

        Spacer(modifier = modifier.height(10.dp))

        Text(
            text = postDay,
            style = OnnaTheme.typography.body7r13,
            color = OnnaTheme.colors.gray6
        )

        Spacer(modifier = modifier.height(24.dp))

        HorizontalDivider(
            thickness = 1.dp,
            color = OnnaTheme.colors.gray1
        )
    }
}
