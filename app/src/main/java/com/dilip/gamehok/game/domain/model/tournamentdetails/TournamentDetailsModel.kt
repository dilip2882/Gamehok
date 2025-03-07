package com.dilip.gamehok.game.domain.model.tournamentdetails

data class TournamentDetailsModel(
    val id: Int,
    val name: String,
    val organizer: String,
    val game: String,
    val entryFee: Int,
    val totalPrize: Int,
    val prizes: List<Prize>,
    val schedule: List<ScheduleItem>,
    val rules: String,
    val contact: String,
    val image: String? = null
)

data class Prize(
    val position: Int,
    val amount: Int
)

data class ScheduleItem(
    val round: String,
    val time: String
)