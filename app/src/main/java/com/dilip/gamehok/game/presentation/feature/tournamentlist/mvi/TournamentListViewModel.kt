package com.dilip.gamehok.game.presentation.feature.tournamentlist.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dilip.gamehok.core.common.functional.fold
import com.dilip.gamehok.game.domain.usecase.TournamentsListUseCase
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
class TournamentListViewModel @Inject constructor(
    private val tournamentListUseCase: TournamentsListUseCase
) : ViewModel(), TournamentListContract {

    private val mutableUIState: MutableStateFlow<TournamentListContract.TournamentListState> =
        MutableStateFlow(TournamentListContract.TournamentListState.Loading)

    private val mutableSharedFlow: MutableSharedFlow<TournamentListContract.TournamentListEffect> =
        MutableSharedFlow()

    override val state: StateFlow<TournamentListContract.TournamentListState>
        get() = mutableUIState

    override val effect: SharedFlow<TournamentListContract.TournamentListEffect>
        get() = mutableSharedFlow.asSharedFlow()

    init {
        loadTournamentList()
    }

    override fun event(event: TournamentListContract.TournamentListEvent) {
        when (event) {
            is TournamentListContract.TournamentListEvent.LoadTournamentList -> {
                loadTournamentList()
            }

            is TournamentListContract.TournamentListEvent.TournamentClicked -> {
                viewModelScope.launch {
                    mutableSharedFlow.emit(
                        TournamentListContract.TournamentListEffect.NavigateToTournamentDetails(
                            event.model
                        )
                    )
                }
            }

            TournamentListContract.TournamentListEvent.TournamentViewAllClicked -> {
                viewModelScope.launch {
                    mutableSharedFlow.emit(TournamentListContract.TournamentListEffect.NavigateToTournamentListGrid)
                }
            }
        }
    }

    private fun loadTournamentList() {
        viewModelScope.launch {
            tournamentListUseCase().fold(
                { updateState(TournamentListContract.TournamentListState.Error(it)) },
                { updateState(TournamentListContract.TournamentListState.Success(gameList = it)) }
            )
        }
    }

    private fun updateState(state: TournamentListContract.TournamentListState) {
        mutableUIState.update { state }
    }
}