package com.dilip.gamehok.game.data.remote.api

import com.dilip.gamehok.game.domain.model.gameslist.GameListItemDto
import com.dilip.gamehok.game.domain.model.tournamentslist.TournamentsListItemDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("games")
    suspend fun getEmailList(): Response<ArrayList<GameListItemDto>>

    @GET("tournaments")
    suspend fun getEmailDetail(): Response<ArrayList<TournamentsListItemDto>>
}