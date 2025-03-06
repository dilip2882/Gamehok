package com.dilip.gamehok.game.domain.model.gameslist

import kotlinx.serialization.Serializable

@Serializable
data class GameListItemModel(
    val id: Int?,
    val gameName: String?,
    val imagePath: String?
)