package br.com.fsamuel.futguess.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SportsSoccer // Ou Gamepad
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import br.com.fsamuel.futguess.navigation.Rotas

@Composable
fun FutGuessBottomBar(
    rotaAtual: String?,
    aoNavegar: (String) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = rotaAtual == Rotas.HOME,
            onClick = { aoNavegar(Rotas.HOME) },
            icon = { Icon(Icons.Default.SportsSoccer, contentDescription = "Game") },
            label = { Text("GAME", fontWeight = FontWeight.Bold) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color(0xFF388E3C),
                indicatorColor = Color(0xFF1B5E20),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        NavigationBarItem(
            selected = rotaAtual == "history",
            onClick = { aoNavegar("history") },
            icon = { Icon(Icons.Default.History, contentDescription = "History") },
            label = { Text("HISTORY", fontWeight = FontWeight.Bold) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color(0xFF388E3C),
                indicatorColor = Color(0xFF1B5E20),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        NavigationBarItem(
            selected = rotaAtual == "profile",
            onClick = { aoNavegar("profile") },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("PROFILE", fontWeight = FontWeight.Bold) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = Color(0xFF388E3C),
                indicatorColor = Color(0xFF1B5E20),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
    }
}