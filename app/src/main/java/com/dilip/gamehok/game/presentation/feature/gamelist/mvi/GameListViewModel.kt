package com.dilip.gamehok.game.presentation.feature.gamelist.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dilip.gamehok.core.common.functional.fold
import com.dilip.gamehok.game.domain.usecase.GameListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    private val getGamesUseCase: GameListUseCase
) : ViewModel(), GameListContract {

    private val mutableUIState: MutableStateFlow<GameListContract.GameListState> =
        MutableStateFlow(GameListContract.GameListState.Loading)

    private val mutableSharedFlow: MutableSharedFlow<GameListContract.GameListEffect> =
        MutableSharedFlow()

    override val state: StateFlow<GameListContract.GameListState>
        get() = mutableUIState

    override val effect: SharedFlow<GameListContract.GameListEffect>
        get() = mutableSharedFlow.asSharedFlow()

    init {
        loadGame()
    }

    override fun event(event: GameListContract.GameListEvent) {
        when (event) {
            is GameListContract.GameListEvent.LoadGameList -> {
                loadGame()
            }

            is GameListContract.GameListEvent.GameClicked -> {
                viewModelScope.launch {
                    mutableSharedFlow.emit(
                        GameListContract.GameListEffect.NavigateToGameDetails(
                            event.model
                        )
                    )
                }
            }

            GameListContract.GameListEvent.GameViewAllClicked -> {
                viewModelScope.launch {
                    mutableSharedFlow.emit(GameListContract.GameListEffect.NavigateToGameListGrid)
                }
            }
        }
    }

    private fun loadGame() {
        viewModelScope.launch {
            getGamesUseCase().fold(
                { updateState(GameListContract.GameListState.Error(it)) },
                { updateState(GameListContract.GameListState.Success(gameList = it)) }
            )
        }
    }

    private fun updateState(state: GameListContract.GameListState) {
        mutableUIState.update { state }
    }
}
