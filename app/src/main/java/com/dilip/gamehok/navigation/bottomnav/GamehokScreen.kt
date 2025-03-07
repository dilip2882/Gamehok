package com.dilip.gamehok.navigation.bottomnav

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dilip.gamehok.game.presentation.chat.ChatScreen
import com.dilip.gamehok.game.presentation.home.HomeScreen
import com.dilip.gamehok.game.presentation.mytournament.MyTournamentScreen
import com.dilip.gamehok.game.presentation.social.SocialScreen
import com.dilip.gamehok.navigation.graph.homeNavGraph

@Composable
fun GamehokScreen(
    navController: NavHostController = rememberNavController(),
) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    val isBottomBarVisible = remember(currentDestination) {
        when (currentDestination?.route) {
            GamehokBottomNavItem.HOME.route,
            GamehokBottomNavItem.MyTournament.route,
            GamehokBottomNavItem.SOCIAL.route,
            GamehokBottomNavItem.CHAT.route,
                -> true

            else -> false
        }
    }

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = isBottomBarVisible,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut(),
            ) {
                GamehokNavBar(
                    currentDestination = currentDestination?.route,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
        },
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = GamehokBottomNavItem.HOME.route,
            modifier = Modifier.padding(paddingValues),
        ) {
            composable(GamehokBottomNavItem.HOME.route) {
                HomeScreen()
            }

            composable(GamehokBottomNavItem.MyTournament.route) {
                MyTournamentScreen()
            }

            composable(GamehokBottomNavItem.SOCIAL.route) {
                SocialScreen()
            }

            composable(GamehokBottomNavItem.CHAT.route) {
                ChatScreen()
            }

            homeNavGraph(navController)
        }
    }
}