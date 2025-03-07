package com.dilip.gamehok.game.data.dto.tournamentdetails

import com.dilip.gamehok.game.domain.model.tournamentdetails.ScheduleItem

data class ScheduleItemDto(
    val round: String,
    val time: String
)

fun ScheduleItemDto.toModel(): ScheduleItem {
    return ScheduleItem(
        round = round,
        time = time
    )
} 