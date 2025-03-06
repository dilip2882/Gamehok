package com.dilip.gamehok.game.domain.model.tournamentslist

import com.dilip.gamehok.game.data.dto.tournamentslist.OrganizerDetails
import kotlinx.serialization.Serializable

@Serializable
data class TournamentsListItemModel(
    val id: Int?,
    val entryFees: Int?,
    val gameName: String?,
    val name: String?,
    val organizerDetails: OrganizerDetails?,
    val prizeCoins: String?,
    val registeredCount: Int?,
    val registrationEndTime: Int?,
    val status: String?,
    val teamSize: String?,
    val thumbnailPath: String?,
    val totalCount: Int?,
    val tournamentStartTime: Int?
)