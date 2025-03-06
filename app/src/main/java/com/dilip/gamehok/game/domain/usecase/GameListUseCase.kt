package com.dilip.gamehok.game.domain.usecase

import com.dilip.gamehok.game.domain.repository.GameRepository
import javax.inject.Inject

class GameListUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend operator fun invoke() = repository.getGameList()
}
