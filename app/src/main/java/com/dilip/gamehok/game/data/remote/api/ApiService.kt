package com.dilip.gamehok.game.data.remote.api

import com.dilip.gamehok.game.data.dto.gameslist.GameListItemDto
import com.dilip.gamehok.game.data.dto.tournamentslist.TournamentsListItemDto
import com.dilip.gamehok.game.data.dto.tournamentdetails.TournamentDetailsDto
import com.dilip.gamehok.game.domain.model.tournamentdetails.TournamentsDetailsItemModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("games")
    suspend fun getGameList(): Response<ArrayList<GameListItemDto>>

    @GET("tournaments")
    suspend fun getTournamentList(): Response<ArrayList<TournamentsListItemDto>>

    @GET("tournaments/{id}")
    suspend fun getTournamentDetails(@Path("id") tournamentId: Int): Response<TournamentDetailsDto>

    @POST("tournaments/{id}/follow")
    suspend fun followTournament(@Path("id") tournamentId: Int): Response<Unit>
}