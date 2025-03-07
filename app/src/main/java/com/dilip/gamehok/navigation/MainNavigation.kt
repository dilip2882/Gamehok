package com.dilip.gamehok.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.dilip.gamehok.navigation.graph.homeNavGraph

@Composable
fun MainNavigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = SubGraph.Home
    ) {
        homeNavGraph(navController)
    }
}