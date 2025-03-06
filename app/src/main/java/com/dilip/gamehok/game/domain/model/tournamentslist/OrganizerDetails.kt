package com.dilip.gamehok.game.domain.model.tournamentslist

import kotlinx.serialization.Serializable

@Serializable
data class OrganizerDetailsModel(
    val id: String?,
    val name: String?,
    val profileImagePath: String?
)