package com.dilip.gamehok.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dilip.gamehok.game.presentation.home.HomeScreen
import com.dilip.gamehok.navigation.Dest
import com.dilip.gamehok.navigation.SubGraph
import com.dilip.gamehok.navigation.bottomnav.GamehokScreen

fun NavGraphBuilder.homeNavGraph(navController: NavController) {

    navigation<SubGraph.Home>(startDestination = Dest.GamehokScreen) {

        composable<Dest.GamehokScreen> {
            GamehokScreen()
        }

        composable<Dest.HomeScreen> {
            HomeScreen()
        }
    }
}