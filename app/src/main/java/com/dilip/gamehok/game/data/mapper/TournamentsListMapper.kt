package com.dilip.gamehok.game.data.mapper

import com.dilip.gamehok.core.common.mapper.ResultMapper
import com.dilip.gamehok.game.data.dto.tournamentslist.TournamentsListItemDto
import com.dilip.gamehok.game.domain.model.tournamentslist.TournamentsListItemModel
import javax.inject.Inject

class TournamentsListMapper @Inject constructor() :
    ResultMapper<List<TournamentsListItemDto>, List<TournamentsListItemModel>> {

    override fun map(input: List<TournamentsListItemDto>): List<TournamentsListItemModel> =
        input.filter {
            it.id != null && it.name != null
        }.map {
            it.toModel()
        }.sortedBy {
            it.tournamentStartTime
        }

    private fun TournamentsListItemDto.toModel() =
        TournamentsListItemModel(
            id = id,
            entryFees = entryFees,
            gameName = gameName,
            name = name,
            organizerDetails = organizerDetails,
            prizeCoins = prizeCoins,
            registeredCount = registeredCount,
            registrationEndTime = registrationEndTime,
            status = status,
            teamSize = teamSize,
            thumbnailPath = thumbnailPath,
            totalCount = totalCount,
            tournamentStartTime = tournamentStartTime
        )
}
