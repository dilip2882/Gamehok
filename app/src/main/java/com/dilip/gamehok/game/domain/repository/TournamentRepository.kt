package com.dilip.gamehok.game.domain.repository

import com.dilip.gamehok.core.common.error.Failure
import com.dilip.gamehok.core.common.functional.Either
import com.dilip.gamehok.game.domain.model.tournamentdetails.TournamentsDetailsItemModel
import com.dilip.gamehok.game.domain.model.tournamentslist.TournamentsListItemModel
import com.dilip.gamehok.game.domain.model.tournamentdetails.TournamentDetailsModel

interface TournamentRepository {
    suspend fun getTournamentList(): Either<Failure, List<TournamentsListItemModel>>

    suspend fun getTournamentDetails(tournamentId: Int): Either<Failure, TournamentDetailsModel>

    suspend fun followTournament(tournamentId: Int): Either<Failure, Unit>
}