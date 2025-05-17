package com.example.android4.presentation.landing

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.android4.R
import com.example.android4.core.designsystem.theme.OnnaTheme

@Composable
fun LandingScreen(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(OnnaTheme.colors.white)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(110.dp))

        MapIcon()

        Spacer(modifier = Modifier.height(70.dp))

        Text(
            text = "부산만 왔다 가시게요?",
            style = OnnaTheme.typography.title4b22,
            color = OnnaTheme.colors.black,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        Text(
            text = "부산 갔다가 이리온나!",
            style = OnnaTheme.typography.title4b22,
            color = OnnaTheme.colors.black,
            modifier = Modifier.padding(bottom = 15.dp)
        )
        Text(
            text = "현지인이 직접 알려주는\n" +
                "경남 관광코스를 경험해보세요",
            style = OnnaTheme.typography.body3r15,
            color = OnnaTheme.colors.gray6,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        LandingButton(
            onButtonClick = onClick
        )

        Spacer(modifier = Modifier.height(72.dp))
    }
}

@Composable
private fun MapIcon(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_map_pin),
            contentDescription = null,
            tint = Color.Unspecified
        )

        val infiniteTransition = rememberInfiniteTransition(label = "pinAnimation")
        val yOffset by infiniteTransition.animateFloat(
            initialValue = -5f,
            targetValue = 5f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 500, delayMillis = 0),
                repeatMode = RepeatMode.Reverse
            ),
            label = "yOffset"
        )

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_pin),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = yOffset.dp)
        )
    }
}

@Composable
private fun LandingButton(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = "큐레이터 구경하기",
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
