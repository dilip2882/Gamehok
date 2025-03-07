package com.dilip.gamehok.game.domain.usecase

import com.dilip.gamehok.core.common.error.Failure
import com.dilip.gamehok.core.common.functional.Either
import com.dilip.gamehok.game.domain.model.tournamentdetails.TournamentDetailsModel
import com.dilip.gamehok.game.domain.repository.TournamentRepository
import javax.inject.Inject

class TournamentDetailsUseCase @Inject constructor(
    private val tournamentRepository: TournamentRepository
) {
    suspend operator fun invoke(tournamentId: Int): Either<Failure, TournamentDetailsModel> {
        return tournamentRepository.getTournamentDetails(tournamentId)
    }
}
