package com.example.android4.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import com.example.android4.core.designsystem.theme.OnnaTheme
import com.example.android4.presentation.main.MainScreen
import com.example.android4.presentation.mypage.MyPageScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            OnnaTheme {
                MyPageScreen(paddingValues = PaddingValues(0.dp))
            }
        }
    }
}
