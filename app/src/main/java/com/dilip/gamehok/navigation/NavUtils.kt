package com.dilip.gamehok.navigation

import kotlinx.serialization.Serializable

sealed class SubGraph {
    @Serializable
    data object Home : SubGraph()

}

sealed interface Dest {

    @Serializable
    data object GamehokScreen : Dest

    @Serializable
    data object HomeScreen : Dest

    @Serializable
    data object MyTournamentScreen : Dest

    @Serializable
    data object Social : Dest

    @Serializable
    data object Chat : Dest

    @Serializable
    data object GameDetailsScreen : Dest

    @Serializable
    data object TournamentDetailsScreen : Dest

}