package com.dilip.gamehok.game.domain.usecase

import com.dilip.gamehok.game.domain.repository.GameRepository
import com.dilip.gamehok.game.domain.repository.TournamentRepository
import javax.inject.Inject

class TournamentsListUseCase  @Inject constructor(
    private val repository: TournamentRepository
) {
    suspend operator fun invoke() = repository.getTournamentList()
}
