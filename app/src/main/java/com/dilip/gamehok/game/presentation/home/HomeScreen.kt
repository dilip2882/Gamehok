package com.dilip.gamehok.game.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dilip.gamehok.core.ui.theme.DarkBackground
import com.dilip.gamehok.game.presentation.feature.gamelist.GameListScreen
import com.dilip.gamehok.game.presentation.feature.gamelist.mvi.GameListContract
import com.dilip.gamehok.game.presentation.feature.gamelist.mvi.GameListViewModel
import com.dilip.gamehok.game.presentation.feature.tournamentlist.TournamentListScreen
import com.dilip.gamehok.game.presentation.feature.tournamentlist.mvi.TournamentListContract
import com.dilip.gamehok.game.presentation.feature.tournamentlist.mvi.TournamentListViewModel

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            HomeTopBar()
        },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(DarkBackground)
        ) {
            item { HomeHeader() }

            item {
                val gameListViewModel: GameListViewModel = hiltViewModel()
                val gameListState by gameListViewModel.state.collectAsStateWithLifecycle()
                val gameListDispatch: (GameListContract.GameListEvent) -> Unit = { event ->
                    gameListViewModel.event(event)
                }
                GameListScreen(
                    state = gameListState,
                    dispatch = gameListDispatch
                )
            }

            item {
                val tournamentViewModel: TournamentListViewModel = hiltViewModel()
                val tournamentState by tournamentViewModel.state.collectAsStateWithLifecycle()
                val tournamentDispatch: (TournamentListContract.TournamentListEvent) -> Unit = { event ->
                    tournamentViewModel.event(event)
                }
                TournamentListScreen(
                    state = tournamentState,
                    dispatch = tournamentDispatch
                )
            }

            item {
                val peopleToFollow = listOf(
                    User(1, "Legend Gamer", "https://i.imgur.com/6AglEUF.jpeg"),
                    User(2, "Legend Gamer", "https://i.imgur.com/FcWKydL.png"),
                    User(3, "Legend Gamer")
                )
                PeopleToFollowScreen(people = peopleToFollow) { user ->

                }
            }
        }
    }
}