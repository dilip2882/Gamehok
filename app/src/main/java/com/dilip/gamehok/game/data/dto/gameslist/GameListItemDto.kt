package com.dilip.gamehok.game.data.dto.gameslist

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameListItemDto(
    @SerialName("gameName")
    val gameName: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("imagePath")
    val imagePath: String?
)