package com.dilip.gamehok

import HomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dilip.gamehok.core.ui.theme.GamehokTheme
import com.dilip.gamehok.game.presentation.gamelist.GameListScreen
import com.dilip.gamehok.game.presentation.gamelist.mvi.GameListContract
import com.dilip.gamehok.game.presentation.gamelist.mvi.GameListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GamehokTheme {
                HomeScreen()
            }
        }
    }
}

