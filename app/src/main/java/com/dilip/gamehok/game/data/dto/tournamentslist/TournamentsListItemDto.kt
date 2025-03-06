package com.dilip.gamehok.game.data.dto.tournamentslist

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TournamentsListItemDto(
    @SerialName("entryFees")
    val entryFees: Int?,
    @SerialName("gameName")
    val gameName: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("organizerDetails")
    val organizerDetails: OrganizerDetails?,
    @SerialName("prizeCoins")
    val prizeCoins: String?,
    @SerialName("registeredCount")
    val registeredCount: Int?,
    @SerialName("registrationEndTime")
    val registrationEndTime: Int?,
    @SerialName("status")
    val status: String?,
    @SerialName("teamSize")
    val teamSize: String?,
    @SerialName("thumbnailPath")
    val thumbnailPath: String?,
    @SerialName("totalCount")
    val totalCount: Int?,
    @SerialName("tournamentStartTime")
    val tournamentStartTime: Int?
)