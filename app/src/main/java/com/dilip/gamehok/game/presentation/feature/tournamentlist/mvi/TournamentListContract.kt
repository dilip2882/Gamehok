package com.dilip.gamehok.game.presentation.feature.tournamentlist.mvi

import com.dilip.gamehok.core.common.error.Failure
import com.dilip.gamehok.core.ui.mvi.MVIContract
import com.dilip.gamehok.game.domain.model.gameslist.GameListItemModel
import com.dilip.gamehok.game.domain.model.tournamentslist.TournamentsListItemModel
import com.dilip.gamehok.game.presentation.feature.gamelist.mvi.GameListContract.GameListEffect
import com.dilip.gamehok.game.presentation.feature.gamelist.mvi.GameListContract.GameListEvent

interface TournamentListContract :
    MVIContract<TournamentListContract.TournamentListState, TournamentListContract.TournamentListEffect, TournamentListContract.TournamentListEvent> {

    sealed class TournamentListEvent {
        data object LoadTournamentList : TournamentListEvent()
        data class TournamentClicked(val model: TournamentsListItemModel) : TournamentListEvent()
        data object TournamentViewAllClicked : TournamentListEvent()

    }

    sealed class TournamentListState {
        data object Loading : TournamentListState()
        data class Success(
            val gameList: List<TournamentsListItemModel>
        ) : TournamentListState()
        data class Error(
            val error: Failure
        ) : TournamentListState()
    }

    sealed class TournamentListEffect {
        data class NavigateToTournamentDetails(val model: TournamentsListItemModel) : TournamentListEffect()
        data object NavigateToTournamentListGrid : TournamentListEffect()

    }
}