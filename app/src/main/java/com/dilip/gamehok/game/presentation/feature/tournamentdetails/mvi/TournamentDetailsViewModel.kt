package com.dilip.gamehok.game.presentation.feature.tournamentdetails.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dilip.gamehok.core.common.error.Failure
import com.dilip.gamehok.game.domain.usecase.FollowTournamentUseCase
import com.dilip.gamehok.game.domain.usecase.TournamentDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TournamentDetailsViewModel @Inject constructor(
    private val tournamentDetailsUseCase: TournamentDetailsUseCase,
    private val followTournamentUseCase: FollowTournamentUseCase
) : ViewModel(), TournamentDetailsContract {

    private val mutableUIState: MutableStateFlow<TournamentDetailsContract.TournamentDetailsState> =
        MutableStateFlow(TournamentDetailsContract.TournamentDetailsState.Loading)

    private val mutableSharedFlow: MutableSharedFlow<TournamentDetailsContract.TournamentDetailsEffect> =
        MutableSharedFlow()

    override val state: StateFlow<TournamentDetailsContract.TournamentDetailsState>
        get() = mutableUIState

    override val effect: SharedFlow<TournamentDetailsContract.TournamentDetailsEffect>
        get() = mutableSharedFlow.asSharedFlow()

    init {
        loadTournamentDetails(1)
    }

    override fun event(event: TournamentDetailsContract.TournamentDetailsEvent) {
        when (event) {
            is TournamentDetailsContract.TournamentDetailsEvent.LoadTournamentDetails -> {
                loadTournamentDetails(event.tournamentId)
            }

            is TournamentDetailsContract.TournamentDetailsEvent.FollowTournament -> {
                followTournament(event.tournamentId)
            }

            is TournamentDetailsContract.TournamentDetailsEvent.JoinTournament -> TODO()
        }
    }

    private fun loadTournamentDetails(tournamentId: Int) {
        viewModelScope.launch {
            mutableUIState.value = TournamentDetailsContract.TournamentDetailsState.Loading
            try {
                val tournamentDetails = tournamentDetailsUseCase(tournamentId)
                mutableUIState.value =
                    TournamentDetailsContract.TournamentDetailsState.Success(tournament = tournamentDetails)
            } catch (e: Exception) {
                mutableUIState.value =
                    TournamentDetailsContract.TournamentDetailsState.Error(Failure.UnknownError(e))
            }
        }
    }

    private fun followTournament(tournamentId: Int) {
        viewModelScope.launch {
            try {
                followTournamentUseCase(tournamentId)
                mutableSharedFlow.emit(TournamentDetailsContract.TournamentDetailsEffect.NavigateToTournamentOverviewTab)
            } catch (e: Exception) {
            }
        }
    }
}
