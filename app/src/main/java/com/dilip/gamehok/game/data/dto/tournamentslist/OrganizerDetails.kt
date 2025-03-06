package com.dilip.gamehok.game.data.dto.tournamentslist

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrganizerDetails(
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("profileImagePath")
    val profileImagePath: String?
)