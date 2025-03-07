package com.dilip.gamehok.game.data.dto.tournamentdetails

import com.dilip.gamehok.game.domain.model.tournamentdetails.Prize

data class PrizeDto(
    val position: Int,
    val amount: Int
)

fun PrizeDto.toModel(): Prize {
    return Prize(
        position = position,
        amount = amount
    )
} 