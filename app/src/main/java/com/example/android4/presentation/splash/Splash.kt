package com.example.android4.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.android4.R
import com.example.android4.core.designsystem.theme.OnnaTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToLanding: () -> Unit,
    modifier: Modifier = Modifier
) {
    var startAnimation by remember { mutableStateOf(false) }
    var rotation by remember { mutableFloatStateOf(0f) }
    var scale by remember { mutableFloatStateOf(1f) }

    LaunchedEffect(Unit) {
        delay(1000)
        startAnimation = true

        val startTime = System.currentTimeMillis()
        val animationDuration = 500L

        while (System.currentTimeMillis() - startTime < animationDuration) {
            val elapsedTime = System.currentTimeMillis() - startTime
            val progress = (elapsedTime.toFloat() / animationDuration).coerceIn(0f, 1f)

            rotation = progress * 720f
            scale = 1f + progress

            delay(10)
        }

        navigateToLanding()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(OnnaTheme.colors.blue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_splash),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .rotate(rotation)
                .scale(scale)
        )
    }
}
