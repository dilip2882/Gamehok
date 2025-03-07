package com.dilip.gamehok.navigation.bottomnav

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dilip.gamehok.core.ui.GamehokIcons
import com.dilip.gamehok.core.ui.theme.DarkGreen

enum class GamehokBottomNavItem(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val label: String,
    val route: String,
) {
    HOME(
        selectedIcon = GamehokIcons.Home,
        unselectedIcon = GamehokIcons.HomeBorder,
        label = "Home",
        route = "gamehok_home",
    ),
    MyTournament(
        selectedIcon = GamehokIcons.MyTournament,
        unselectedIcon = GamehokIcons.MyTournament,
        label = "My Tournament",
        route = "gamehok_my_tournament",
    ),
    SOCIAL(
        selectedIcon = GamehokIcons.Social,
        unselectedIcon = GamehokIcons.Social,
        label = "Social",
        route = "gamehok_social",
    ),
    CHAT(
        selectedIcon = GamehokIcons.Chat,
        unselectedIcon = GamehokIcons.Chat,
        label = "Chat",
        route = "gamehok_chat",
    );

}

@Composable
fun GamehokNavBar(
    currentDestination: String?,
    onNavigate: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            GamehokBottomNavItem.entries.forEach { item ->
                val selected = currentDestination == item.route

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                        .clickable { onNavigate(item.route) },
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        val iconSize by animateDpAsState(
                            targetValue = if (selected) 30.dp else 30.dp,
                            animationSpec = tween(durationMillis = 300),
                            label = "Icon Size",
                        )

                        val iconShape by animateFloatAsState(
                            targetValue = if (selected) 35f else 20f,
                            animationSpec = tween(durationMillis = 300),
                            label = "Icon Shape",
                        )

                        val backgroundWidth by animateDpAsState(
                            targetValue = if (selected) 60.dp else 40.dp,
                            animationSpec = tween(durationMillis = 300),
                            label = "Background Width",
                        )

                        val rotation by animateFloatAsState(
                            targetValue = if (selected && item == GamehokBottomNavItem.MyTournament) 90f else 0f,
                            animationSpec = tween(durationMillis = 300),
                            label = "Rotation",
                        )

                        Box(
                            modifier = Modifier
                                .width(backgroundWidth)
                                .size(iconSize)
                                .background(
                                    color = if (selected) Color.Green else Color.Transparent,
                                    shape = RoundedCornerShape(iconShape),
                                )
                                .graphicsLayer(rotationZ = rotation),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.label,
                                modifier = Modifier.size(iconSize),
                                tint = if (selected) Color(0xFF08090A) else Color.White,
                            )
                        }

                        val labelColor by animateColorAsState(
                            targetValue = if (selected) Color(0xFFE7EF9F) else Color(0xFFB0B0B0),
                            animationSpec = tween(durationMillis = 300),
                            label = "Label Color",
                        )

                        val labelFontSize by animateFloatAsState(
                            targetValue = if (selected) 14f else 12f,
                            animationSpec = tween(durationMillis = 300),
                            label = "Label Font Size",
                        )

                        Text(
                            text = item.label,
                            fontSize = labelFontSize.sp,
                            color = labelColor,
                            modifier = Modifier.padding(top = 4.dp),
                            fontWeight = FontWeight.Medium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GamehokNavBarPreview() {
    val currentDestination = "home"
    val onNavigate: (String) -> Unit = { }

    GamehokNavBar(
        currentDestination = currentDestination,
        onNavigate = onNavigate,
    )
}
