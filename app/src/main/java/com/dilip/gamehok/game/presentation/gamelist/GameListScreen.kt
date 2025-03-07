package com.dilip.gamehok.game.presentation.gamelist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.dilip.gamehok.R
import com.dilip.gamehok.core.common.error.getErrorMessage
import com.dilip.gamehok.core.ui.components.FullScreenError
import com.dilip.gamehok.core.ui.components.LinearFullScreenProgress
import com.dilip.gamehok.core.ui.theme.DarkBackground
import com.dilip.gamehok.core.ui.theme.DarkSurface
import com.dilip.gamehok.core.ui.theme.Dimensions
import com.dilip.gamehok.core.ui.theme.GoldText
import com.dilip.gamehok.core.ui.theme.LightText
import com.dilip.gamehok.game.presentation.gamelist.mvi.GameListContract

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
    LazyColumn(
        modifier = Modifier
            .padding(Dimensions.dimen_8)
            .background(DarkBackground)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = Dimensions.dimen_4),
                horizontalArrangement = Arrangement.SpaceBetween
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

            LazyRow(modifier = Modifier.padding(vertical = Dimensions.dimen_4)) {
                items(
                    states.gameList,
                    key = { it.id ?: 0 }
                ) { gameItem ->
                    GameItem(
                        modifier = Modifier
                            .padding(horizontal = Dimensions.dimen_4)
                            .width(100.dp)
                            .height(150.dp),
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
}

@Composable
fun GameItem(
    modifier: Modifier = Modifier,
    image: String?,
    gameName: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .background(DarkSurface, shape = MaterialTheme.shapes.small)
            .padding(Dimensions.dimen_4)
            .clickable(onClick = onClick)
    ) {
        image?.let {
            if (it.isNotEmpty()) {
                val painter = rememberAsyncImagePainter(it)
                Image(
                    painter = painter,
                    contentDescription = gameName,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                )
            } else {
                val placeholder = painterResource(id = R.drawable.gamehok_logo)
                Image(
                    painter = placeholder,
                    contentDescription = gameName,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                )
            }
        }

        Text(
            text = gameName,
            style = MaterialTheme.typography.bodySmall.copy(color = LightText),
            modifier = Modifier
                .padding(top = Dimensions.dimen_4)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}
