package com.dilip.gamehok.game.domain.model.tournamentdetails

import com.dilip.gamehok.game.data.dto.tournamentslist.OrganizerDetails
import kotlinx.serialization.Serializable

@Serializable
data class TournamentsDetailsItemModel(
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
    val tournamentStartTime: Int?,
    val description: String?,
    val rules: String?,
    val location: String?,
    val contactInfo: String?,
    val termsAndConditions: String?,
    val prizeBreakdown: String?,
    val bracketFormat: String?,
    val isLive: Boolean?
)
