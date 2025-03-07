package com.dilip.gamehok.game.data.repository

import com.dilip.gamehok.core.common.error.Failure
import com.dilip.gamehok.core.common.functional.Either
import com.dilip.gamehok.game.data.dto.tournamentdetails.toModel
import com.dilip.gamehok.game.data.mapper.TournamentsListMapper
import com.dilip.gamehok.game.data.remote.api.ApiService
import com.dilip.gamehok.game.data.remote.handler.safeApiCall
import com.dilip.gamehok.game.domain.model.tournamentdetails.TournamentDetailsModel
import com.dilip.gamehok.game.domain.model.tournamentslist.TournamentsListItemModel
import com.dilip.gamehok.game.domain.repository.TournamentRepository
import java.io.IOException
import javax.inject.Inject

class TournamentRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val tournamentListMapper: TournamentsListMapper,
) : TournamentRepository {
    override suspend fun getTournamentList(): Either<Failure, List<TournamentsListItemModel>> =
        safeApiCall(
            apiCall = { apiService.getTournamentList() },
            mapper = { tournamentListMapper.map(it) }
        )

    override suspend fun getTournamentDetails(tournamentId: Int): Either<Failure, TournamentDetailsModel> {
        return try {
            val response = apiService.getTournamentDetails(tournamentId)
            if (response.isSuccessful) {
                response.body()?.let { tournamentDetails ->
                    Either.Right(tournamentDetails.toModel())
                } ?: Either.Left(Failure.ServerError(0, "No data found"))
            } else {
                Either.Left(Failure.ServerError(response.code(), response.message()))
            }
        } catch (e: IOException) {
            Either.Left(Failure.NetworkError(e))
        } catch (e: Exception) {
            Either.Left(Failure.UnknownError(e))
        }
    }

    override suspend fun followTournament(tournamentId: Int): Either<Failure, Unit> {
        return try {
            val response = apiService.followTournament(tournamentId)
            if (response.isSuccessful) {
                Either.Right(Unit)
            } else {
                Either.Left(Failure.ServerError(response.code(), response.message()))
            }
        } catch (e: IOException) {
            Either.Left(Failure.NetworkError(e))
        } catch (e: Exception) {
            Either.Left(Failure.UnknownError(e))
        }
    }
}