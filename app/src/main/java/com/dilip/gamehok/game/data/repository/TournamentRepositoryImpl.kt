package com.dilip.gamehok.game.data.repository

import com.dilip.gamehok.core.common.error.Failure
import com.dilip.gamehok.core.common.functional.Either
import com.dilip.gamehok.game.data.mapper.GameListMapper
import com.dilip.gamehok.game.data.mapper.TournamentsListMapper
import com.dilip.gamehok.game.data.remote.api.ApiService
import com.dilip.gamehok.game.data.remote.handler.safeApiCall
import com.dilip.gamehok.game.domain.model.gameslist.GameListItemModel
import com.dilip.gamehok.game.domain.model.tournamentdetails.TournamentsDetailsItemModel
import com.dilip.gamehok.game.domain.model.tournamentslist.TournamentsListItemModel
import com.dilip.gamehok.game.domain.repository.GameRepository
import com.dilip.gamehok.game.domain.repository.TournamentRepository
import javax.inject.Inject

class TournamentRepositoryImpl@Inject constructor(
    private val apiService: ApiService,
    private val tournamentListMapper: TournamentsListMapper,
): TournamentRepository {
    override suspend fun getTournamentList(): Either<Failure, List<TournamentsListItemModel>> =
        safeApiCall(
            apiCall = { apiService.getTournamentList() },
            mapper = { tournamentListMapper.map(it) }
        )

}