package com.dilip.gamehok.game.domain.repository

import com.dilip.gamehok.core.common.error.Failure
import com.dilip.gamehok.core.common.functional.Either
import com.dilip.gamehok.game.domain.model.tournamentdetails.TournamentsDetailsItemModel
import com.dilip.gamehok.game.domain.model.tournamentslist.TournamentsListItemModel

interface TournamentRepository {
    suspend fun getTournamentList(): Either<Failure, List<TournamentsListItemModel>>
}