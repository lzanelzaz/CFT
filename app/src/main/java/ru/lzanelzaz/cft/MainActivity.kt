package ru.lzanelzaz.cft

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ru.lzanelzaz.cft.ui.screens.Screen
import ru.lzanelzaz.cft.ui.theme.JetpackTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackTheme {
                Screen()
            }
        }
    }
}
