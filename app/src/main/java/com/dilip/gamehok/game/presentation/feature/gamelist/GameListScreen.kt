package com.dilip.gamehok.game.presentation.feature.gamelist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.dilip.gamehok.R
import com.dilip.gamehok.core.common.error.getErrorMessage
import com.dilip.gamehok.core.ui.components.FullScreenError
import com.dilip.gamehok.core.ui.components.LinearFullScreenProgress
import com.dilip.gamehok.core.ui.theme.DarkBackground
import com.dilip.gamehok.core.ui.theme.Dimensions
import com.dilip.gamehok.core.ui.theme.GoldText
import com.dilip.gamehok.core.ui.theme.LightText
import com.dilip.gamehok.game.presentation.feature.gamelist.mvi.GameListContract

@Composable
fun GameListScreen(
    state: GameListContract.GameListState,
    dispatch: (GameListContract.GameListEvent) -> Unit
) {
    when (state) {
        is GameListContract.GameListState.Error -> FullScreenError(
            errorMessage = state.error.getErrorMessage()
        )

        GameListContract.GameListState.Loading -> LinearFullScreenProgress(
            modifier = Modifier.semantics {
                contentDescription = "Loading"
            }
        )

        is GameListContract.GameListState.Success -> GameListUi(state, dispatch)
    }
}

@Composable
fun GameListUi(
    states: GameListContract.GameListState.Success,
    dispatch: (GameListContract.GameListEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkBackground)
            .padding(Dimensions.dimen_8)
    ) {
        // Header Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Dimensions.dimen_8),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Play Tournament by Games",
                style = MaterialTheme.typography.titleMedium.copy(color = LightText)
            )
            Text(
                text = "View All",
                style = MaterialTheme.typography.bodyMedium.copy(color = GoldText),
                modifier = Modifier
                    .clickable { dispatch(GameListContract.GameListEvent.GameViewAllClicked) }
            )
        }

        // Game List
        LazyRow(
            modifier = Modifier.padding(vertical = Dimensions.dimen_4),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(
                states.gameList,
                key = { it.id ?: 0 }
            ) { gameItem ->
                GameItem(
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .width(120.dp),
                    gameName = gameItem.gameName ?: "Unknown Game",
                    image = gameItem.imagePath,
                    onClick = {
                        dispatch(GameListContract.GameListEvent.GameClicked(gameItem))
                    }
                )
            }
        }
    }
}

@Composable
fun GameItem(
    modifier: Modifier = Modifier,
    image: String?,
    gameName: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .size(width = 120.dp, height = 100.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            val painter = if (!image.isNullOrEmpty()) {
                rememberAsyncImagePainter(image)
            } else {
                painterResource(id = R.drawable.gamehok_logo)
            }

            Image(
                painter = painter,
                contentDescription = gameName,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(
            text = gameName,
            style = MaterialTheme.typography.bodyMedium.copy(color = LightText),
            modifier = Modifier
                .padding(top = 6.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
    }
}
