package com.dilip.gamehok.game.presentation.gamelist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.dilip.gamehok.core.ui.theme.DarkBackground
import com.dilip.gamehok.core.ui.theme.LightText
import com.dilip.gamehok.game.domain.model.gameslist.GameListItemModel

@Composable
fun GameListGridScreen(gameList: List<GameListItemModel>, onGameClicked: (GameListItemModel) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(gameList) { gameItem ->
            GameGridItem(
                gameItem = gameItem,
                onClick = { onGameClicked(gameItem) }
            )
        }
    }
}

@Composable
fun GameGridItem(gameItem: GameListItemModel, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
            .height(150.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val painter = rememberAsyncImagePainter(gameItem.imagePath)
        Image(
            painter = painter,
            contentDescription = gameItem.gameName,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
        )
        Text(
            text = gameItem.gameName ?: "Unknown Game",
            style = MaterialTheme.typography.bodySmall.copy(color = LightText),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}