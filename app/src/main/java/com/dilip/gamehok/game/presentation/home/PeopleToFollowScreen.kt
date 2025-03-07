package com.dilip.gamehok.game.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.dilip.gamehok.R
import com.dilip.gamehok.core.ui.theme.DarkBackground
import com.dilip.gamehok.core.ui.theme.LightText

data class User(
    val id: Int,
    val name: String,
    val profileImage: String? = null,
)

@Composable
fun PeopleToFollowScreen(people: List<User>, onFollowClicked: (User) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkBackground)
            .padding(16.dp)
    ) {
        Text(
            text = "People to follow",
            style = MaterialTheme.typography.titleMedium.copy(color = LightText)
        )

        people.forEach { user ->
            UserItem(user = user, onFollowClicked = onFollowClicked)
        }
    }
}

@Composable
fun UserItem(user: User, onFollowClicked: (User) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onFollowClicked(user) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val painter = rememberAsyncImagePainter(
            model = user.profileImage,
            placeholder = painterResource(id = R.drawable.gamehok_logo),
            error = painterResource(id = R.drawable.gamehok_logo)
        )

        Image(
            painter = painter,
            contentDescription = user.name,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 8.dp)
        )

        Text(
            text = user.name,
            style = MaterialTheme.typography.bodyMedium.copy(color = LightText),
            modifier = Modifier.weight(1f)
        )

        Button(
            onClick = { onFollowClicked(user) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF107C1D)) // Adjust color as needed
        ) {
            Text(text = "Follow", color = Color.White)
        }
    }
}
