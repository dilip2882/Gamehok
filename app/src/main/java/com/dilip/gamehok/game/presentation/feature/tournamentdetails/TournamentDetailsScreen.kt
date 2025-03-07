package com.dilip.gamehok.game.presentation.feature.tournamentdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.dilip.gamehok.core.common.error.getErrorMessage
import com.dilip.gamehok.core.common.functional.fold
import com.dilip.gamehok.core.ui.components.FullScreenError
import com.dilip.gamehok.core.ui.components.LinearFullScreenProgress
import com.dilip.gamehok.game.presentation.feature.tournamentdetails.mvi.TournamentDetailsContract

@Composable
fun TournamentDetailsScreen(
    state: TournamentDetailsContract.TournamentDetailsState,
    dispatch: (TournamentDetailsContract.TournamentDetailsEvent) -> Unit
) {
    when (state) {
        is TournamentDetailsContract.TournamentDetailsState.Error -> FullScreenError(
            errorMessage = state.error.getErrorMessage()
        )

        TournamentDetailsContract.TournamentDetailsState.Loading -> LinearFullScreenProgress(
            modifier = Modifier.semantics {
                contentDescription = "Loading"
            }
        )

        is TournamentDetailsContract.TournamentDetailsState.Success -> TournamentDetailsUi(
            state,
            dispatch
        )
    }
}

@Composable
fun TournamentDetailsUi(
    state: TournamentDetailsContract.TournamentDetailsState.Success,
    dispatch: (TournamentDetailsContract.TournamentDetailsEvent) -> Unit
) {
    state.tournament.fold(
        { failure ->
            FullScreenError(failure.getErrorMessage())
        },
        { tournamentDetails ->
            tournamentDetails.let { tournament ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF0F1B2D))
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = tournament.image),
                        contentDescription = tournament.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )

                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = tournament.name,
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.White
                        )
                        Text(
                            text = "By ${tournament.organizer}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                        Text(text = "Entry Fee: ${tournament.entryFee}", color = Color.White)
                        Text(
                            text = "Total Prize: ${tournament.totalPrize}",
                            color = Color(0xFF22C55E)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Prizes",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                        tournament.prizes.forEach { prize ->
                            Text(
                                text = "${prize.position} Place: ${prize.amount}",
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Schedule",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                        tournament.schedule.forEach { scheduleItem ->
                            Text(
                                text = "${scheduleItem.round}: ${scheduleItem.time}",
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                dispatch(
                                    TournamentDetailsContract.TournamentDetailsEvent.JoinTournament(
                                        tournament.id
                                    )
                                )
                            }
                        ) {
                            Text(text = "Join Tournament")
                        }
                    }
                }
            }
        }
    )
}