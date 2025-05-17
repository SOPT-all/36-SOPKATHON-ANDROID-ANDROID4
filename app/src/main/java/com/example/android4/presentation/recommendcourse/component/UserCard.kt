package com.example.android4.presentation.recommendcourse.component

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.util.TableInfo
import com.example.android4.core.designsystem.component.UrlImage
import com.example.android4.core.designsystem.theme.OnnaTheme

@Composable
fun UserCard(

    modifier: Modifier = Modifier,
    curatorName: String,
    curatorInfo: String
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(OnnaTheme.colors.baseLine)


    ) {

        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 15.dp)
        ) {

            UrlImage(
                imageUrl = "https://avatars.githubusercontent.com/u/142514626?v=4",
                shape = CircleShape,
                modifier = Modifier
                    .size(66.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = curatorName,
                    style = OnnaTheme.typography.title1b17,
                    color = OnnaTheme.colors.black
                )

                Spacer(modifier = Modifier.height(6.dp))


                Text(
                    text = curatorInfo,
                    style = OnnaTheme.typography.body7r13,
                    color = OnnaTheme.colors.gray5,
                    maxLines = 2
                )

            }

        }


    }


}


@Preview
@Composable
private fun UserCardView() {

    UserCard(curatorName = "김뚝딱",
        curatorInfo = "큐레이터에 대한 설명 \n" +
                "경남 지역에서 25년째 살고있는 어쩌구")

}