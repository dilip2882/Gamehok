package com.dilip.gamehok.game.presentation.feature.tournamentlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.dilip.gamehok.R
import com.dilip.gamehok.core.common.error.getErrorMessage
import com.dilip.gamehok.core.ui.components.FullScreenError
import com.dilip.gamehok.core.ui.components.LinearFullScreenProgress
import com.dilip.gamehok.core.ui.theme.DarkGreen
import com.dilip.gamehok.core.ui.theme.DarkSurface
import com.dilip.gamehok.core.ui.theme.GreenAccent
import com.dilip.gamehok.game.domain.model.tournamentslist.TournamentsListItemModel
import com.dilip.gamehok.game.presentation.feature.tournamentlist.mvi.TournamentListContract

@Composable
fun TournamentListScreen(
    state: TournamentListContract.TournamentListState,
    dispatch: (TournamentListContract.TournamentListEvent) -> Unit
) {
    when (state) {
        is TournamentListContract.TournamentListState.Error -> FullScreenError(
            errorMessage = state.error.getErrorMessage()
        )

        TournamentListContract.TournamentListState.Loading -> LinearFullScreenProgress(
            modifier = Modifier.semantics {
                contentDescription = "Loading"
            }
        )

        is TournamentListContract.TournamentListState.Success -> TournamentListUi(state, dispatch)
    }
}

@Composable
fun TournamentListUi(
    states: TournamentListContract.TournamentListState.Success,
    dispatch: (TournamentListContract.TournamentListEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF0F1B2D))
            .padding(16.dp)
    ) {
        // Header Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Compete in Battles",
                style = MaterialTheme.typography.titleLarge.copy(color = Color.White)
            )
            Text(
                text = "View All",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF22C55E)),
                modifier = Modifier
                    .clickable { dispatch(TournamentListContract.TournamentListEvent.TournamentViewAllClicked) }
            )
        }

        // Tournament List
        LazyRow(
            modifier = Modifier.padding(vertical = 8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(
                states.gameList,
                key = { it.id ?: 0 }
            ) { tournamentItem ->
                TournamentItem(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .width(320.dp),
                    tournamentItem = tournamentItem,
                    onClick = {
                        dispatch(
                            TournamentListContract.TournamentListEvent.TournamentClicked(
                                tournamentItem
                            )
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun TournamentItem(
    modifier: Modifier = Modifier,
    tournamentItem: TournamentsListItemModel,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.background(Color(0xFF1E293B))
        ) {
            val painter = if (!tournamentItem.thumbnailPath.isNullOrEmpty()) {
                rememberAsyncImagePainter(tournamentItem.thumbnailPath)
            } else {
                painterResource(id = R.drawable.gamehok_logo)
            }

            Box {
                Image(
                    painter = painter,
                    contentDescription = tournamentItem.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TournamentStatusBadge(tournamentItem.status)
                    Text(
                        text = "${tournamentItem.registeredCount}/${tournamentItem.totalCount}",
                        color = Color.White,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(8.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = tournamentItem.name ?: "Unknown Tournament",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
                )
                Text(
                    text = "By ${tournamentItem.organizerDetails?.name ?: "Unknown"}",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                )
                Row(
                    modifier = Modifier
                        .padding(6.dp)
                ) {
                    TournamentTag(tournamentItem.gameName ?: "Unknown")
                    TournamentTag(tournamentItem.teamSize ?: "Solo")
                    TournamentTag("Entry-${tournamentItem.entryFees ?: 0}")
                }
                Row(
                    modifier = Modifier.padding(top = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = "Time",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Starts ${tournamentItem.tournamentStartTime ?: "N/A"}",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                    )
                }
                Row(
                    modifier = Modifier.padding(top = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AttachMoney,
                        contentDescription = "Prize",
                        tint = Color(0xFF22C55E),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Prize Pool- ${tournamentItem.prizeCoins ?: "0"} 🏆",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFF22C55E))
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = "Go to details",
                                tint = Color(0xFF22C55E),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TournamentTag(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.surface,
        fontStyle = FontStyle.Normal,
        fontSize = 14.sp,
        modifier = Modifier
            .background(GreenAccent, RoundedCornerShape(8.dp))
            .padding(horizontal = 15.dp, vertical = 4.dp)
    )
}

@Composable
fun TournamentStatusBadge(status: String?) {
    Box(
        modifier = Modifier
            .background(GreenAccent, RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(text = status ?: "", color = Color.White, fontSize = 14.sp)
    }
}