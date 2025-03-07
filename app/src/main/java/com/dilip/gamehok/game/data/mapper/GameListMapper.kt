package com.dilip.gamehok.game.data.mapper

import com.dilip.gamehok.core.common.mapper.ResultMapper
import com.dilip.gamehok.game.data.dto.gameslist.GameListItemDto
import com.dilip.gamehok.game.domain.model.gameslist.GameListItemModel
import javax.inject.Inject

class GameListMapper @Inject constructor() :
    ResultMapper<List<GameListItemDto>, List<GameListItemModel>> {

    override fun map(input: List<GameListItemDto>): List<GameListItemModel> =
        input.filter {
            it.id != null && it.gameName != null
        }.map {
            it.toModel()
        }.sortedBy {
            it.gameName
        }

    private fun GameListItemDto.toModel() =
        GameListItemModel(
            id = id!!,
            gameName = gameName!!,
            imagePath = imagePath
        )
}
