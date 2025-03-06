package com.dilip.gamehok.game.domain.repository

import com.dilip.gamehok.core.common.error.Failure
import com.dilip.gamehok.core.common.functional.Either
import com.dilip.gamehok.game.domain.model.gameslist.GameListItemModel

interface GameRepository {
    suspend fun getGameList(): Either<Failure, List<GameListItemModel>>
}