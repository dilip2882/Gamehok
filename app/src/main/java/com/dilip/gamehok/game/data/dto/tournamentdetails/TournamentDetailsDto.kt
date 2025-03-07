package com.dilip.gamehok.game.data.dto.tournamentdetails

import com.dilip.gamehok.game.domain.model.tournamentdetails.TournamentDetailsModel

data class TournamentDetailsDto(
    val id: Int,
    val name: String,
    val organizer: String,
    val game: String,
    val entryFee: Int,
    val totalPrize: Int,
    val prizes: List<PrizeDto>,
    val schedule: List<ScheduleItemDto>,
    val rules: String,
    val contact: String
)

fun TournamentDetailsDto.toModel(): TournamentDetailsModel {
    return TournamentDetailsModel(
        id = id,
        name = name,
        organizer = organizer,
        game = game,
        entryFee = entryFee,
        totalPrize = totalPrize,
        prizes = prizes.map { it.toModel() },
        schedule = schedule.map { it.toModel() },
        rules = rules,
        contact = contact
    )
}

