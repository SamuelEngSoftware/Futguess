package br.com.fsamuel.futguess.ui.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fsamuel.futguess.navigation.Rotas
import br.com.fsamuel.futguess.ui.components.FutGuessBottomBar
import br.com.fsamuel.futguess.ui.theme.VerdeBotao
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HistoryScreen(
    navController: NavController,
    viewModel: HistoryViewModel
) {
    val listaPartidas by viewModel.historico.collectAsState(initial = emptyList())

    Scaffold(
        bottomBar = {
            FutGuessBottomBar(
                rotaAtual = Rotas.HISTORY,
                aoNavegar = { rota -> navController.navigate(rota) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Histórico de Partidas",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (listaPartidas.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Nenhuma partida jogada ainda.", color = Color.Gray)
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(listaPartidas) { partida ->
                        CardPartida(partida)
                    }
                }
            }
        }
    }
}

@Composable
fun CardPartida(partida: br.com.fsamuel.futguess.data.Partida) {
    val corStatus = if (partida.ganhou) VerdeBotao else Color.Red
    val textoStatus = if (partida.ganhou) "VITÓRIA" else "DERROTA"

    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = partida.jogadorSorteado,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Formata a data (ex: 12/05/2024 14:30)
                val formatador = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                val dataString = formatador.format(Date(partida.dataHora))

                Text(
                    text = dataString,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = textoStatus,
                    color = corStatus,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${partida.tentativasUsadas} tentativas",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}