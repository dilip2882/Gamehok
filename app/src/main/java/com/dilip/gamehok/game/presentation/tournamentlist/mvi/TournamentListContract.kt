package com.dilip.gamehok.game.presentation.tournamentlist.mvi

import com.dilip.gamehok.core.common.error.Failure
import com.dilip.gamehok.core.ui.mvi.MVIContract
import com.dilip.gamehok.game.domain.model.gameslist.GameListItemModel
import com.dilip.gamehok.game.domain.model.tournamentslist.TournamentsListItemModel

interface TournamentListContract :
    MVIContract<TournamentListContract.TournamentListState, TournamentListContract.TournamentListEffect, TournamentListContract.TournamentListEvent> {

    sealed class TournamentListEvent {
        data object LoadTournamentList : TournamentListEvent()
        data class TournamentClicked(val model: TournamentsListItemModel) : TournamentListEvent()
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
    }
}