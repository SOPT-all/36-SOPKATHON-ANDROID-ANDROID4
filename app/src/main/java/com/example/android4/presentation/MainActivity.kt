package com.example.android4.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.android4.core.designsystem.theme.ANDROID4Theme
import com.example.android4.presentation.main.MainScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.android4.core.designsystem.theme.OnnaTheme
import com.example.android4.presentation.dummy.DummyRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnnaTheme {
                MainScreen()
            }
        }
    }
}
