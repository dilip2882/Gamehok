package com.dilip.gamehok

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dilip.gamehok.core.ui.theme.GamehokTheme
import com.dilip.gamehok.game.presentation.feature.tournamentdetails.TournamentDetailsScreen
import com.dilip.gamehok.game.presentation.feature.tournamentdetails.mvi.TournamentDetailsContract
import com.dilip.gamehok.game.presentation.feature.tournamentdetails.mvi.TournamentDetailsViewModel
import com.dilip.gamehok.navigation.MainNavigation
import com.dilip.gamehok.navigation.SubGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var navController: NavHostController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            )
        )
        setContent {
            GamehokTheme {
                navController = rememberNavController()
                MainNavigation(
                    navController = navController!!,
                )
            }
        }
    }
}

