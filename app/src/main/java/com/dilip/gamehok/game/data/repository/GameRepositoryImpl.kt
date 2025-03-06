package com.dilip.gamehok.game.data.repository

import com.dilip.gamehok.core.common.error.Failure
import com.dilip.gamehok.core.common.functional.Either
import com.dilip.gamehok.game.data.mapper.GameListMapper
import com.dilip.gamehok.game.data.remote.api.ApiService
import com.dilip.gamehok.game.data.remote.handler.safeApiCall
import com.dilip.gamehok.game.domain.model.gameslist.GameListItemModel
import com.dilip.gamehok.game.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val gameListMapper: GameListMapper,
    ): GameRepository {
    override suspend fun getGameList(): Either<Failure, List<GameListItemModel>> =
        safeApiCall(
            apiCall = { apiService.getGameList() },
            mapper = { gameListMapper.map(it) }
        )
}