package com.dilip.gamehok.game.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsCompat
import com.dilip.gamehok.core.ui.GamehokIcons
import com.dilip.gamehok.core.ui.theme.DarkBackground

@Composable
fun HomeTopBar() {
    val view = LocalView.current
    val insets = view.context.resources.displayMetrics.density

    val topInset = with(LocalDensity.current) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            view.rootWindowInsets?.getInsets(WindowInsetsCompat.Type.statusBars())?.top?.toDp() ?: 0.dp
        } else {
            val resources = LocalContext.current.resources
            val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                val statusBarHeightPx = resources.getDimensionPixelSize(resourceId)
                statusBarHeightPx.toDp()
            } else {
                0.dp
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkBackground)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            imageVector = GamehokIcons.Person,
            contentDescription = "Profile",
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .border(2.dp, Color.White, CircleShape),
            colorFilter = ColorFilter.tint(Color.White)
        )

        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF107C1D)),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = GamehokIcons.Note, contentDescription = "Tickets", tint = Color.White, modifier = Modifier.size(16.dp))
                Text(text = "245", color = Color.White, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(start = 4.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Icon(imageVector = GamehokIcons.Coin, contentDescription = "Coins", tint = Color.White, modifier = Modifier.size(16.dp))
                Text(text = "2456", color = Color.White, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(start = 4.dp))
            }
        }

        Icon(
            imageVector = GamehokIcons.NotificationBorder,
            contentDescription = "Notifications",
            tint = Color.White,
            modifier = Modifier.size(30.dp)
        )
    }
}
