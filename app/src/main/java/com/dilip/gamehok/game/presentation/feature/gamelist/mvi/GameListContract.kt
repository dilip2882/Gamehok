package com.dilip.gamehok.game.presentation.feature.gamelist.mvi

import com.dilip.gamehok.core.common.error.Failure
import com.dilip.gamehok.core.ui.mvi.MVIContract
import com.dilip.gamehok.game.domain.model.gameslist.GameListItemModel

interface GameListContract :
    MVIContract<GameListContract.GameListState, GameListContract.GameListEffect, GameListContract.GameListEvent> {

    sealed class GameListEvent {
        data object LoadGameList : GameListEvent()
        data class GameClicked(val model: GameListItemModel) : GameListEvent()
        data object GameViewAllClicked : GameListEvent()
    }

    sealed class GameListState {
        data object Loading : GameListState()
        data class Success(
            val gameList: List<GameListItemModel>
        ) : GameListState()
        data class Error(
            val error: Failure
        ) : GameListState()
    }

    sealed class GameListEffect {
        data class NavigateToGameDetails(val model: GameListItemModel) : GameListEffect()
        data object NavigateToGameListGrid : GameListEffect()
    }
}