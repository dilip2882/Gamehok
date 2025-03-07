package com.dilip.gamehok.game.presentation.feature.tournamentdetails.mvi

import com.dilip.gamehok.core.common.error.Failure
import com.dilip.gamehok.core.common.functional.Either
import com.dilip.gamehok.core.ui.mvi.MVIContract
import com.dilip.gamehok.game.domain.model.tournamentdetails.TournamentDetailsModel

interface TournamentDetailsContract :
    MVIContract<TournamentDetailsContract.TournamentDetailsState, TournamentDetailsContract.TournamentDetailsEffect, TournamentDetailsContract.TournamentDetailsEvent> {

    sealed class TournamentDetailsState {
        data object Loading : TournamentDetailsState()
        data class Success(
            val tournament: Either<Failure, TournamentDetailsModel>
        ) : TournamentDetailsState()
        data class Error(
            val error: Failure
        ) : TournamentDetailsState()
    }

    sealed class TournamentDetailsEvent {
        data class LoadTournamentDetails(val tournamentId: Int) : TournamentDetailsEvent()
        data class FollowTournament(val tournamentId: Int) : TournamentDetailsEvent()
        data class JoinTournament(val tournamentId: Int) : TournamentDetailsEvent()
    }

    sealed class TournamentDetailsEffect {
        object NavigateToTournamentOverviewTab : TournamentDetailsEffect()
    }
}