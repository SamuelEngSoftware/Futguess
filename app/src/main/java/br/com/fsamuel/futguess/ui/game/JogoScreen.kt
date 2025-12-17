package br.com.fsamuel.futguess.ui.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import br.com.fsamuel.futguess.navigation.Rotas
import br.com.fsamuel.futguess.ui.components.FutGuessBottomBar
import br.com.fsamuel.futguess.ui.components.FutGuessTopBar
import br.com.fsamuel.futguess.ui.theme.CorCerta
import br.com.fsamuel.futguess.ui.theme.CorLugarErrado
import br.com.fsamuel.futguess.ui.theme.CorNaoExiste
import br.com.fsamuel.futguess.ui.theme.CorPadrao
import br.com.fsamuel.futguess.ui.theme.TextoBranco
import androidx.compose.ui.platform.LocalConfiguration
import  androidx.compose.ui.unit.min


@Composable
fun JogoScreen(
    navController: NavController,
    viewModel: JogoViewModel = viewModel()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val rotaAtual = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            FutGuessTopBar(
                onSettingsClick = { }, // AQUI TU COLOCA A ROTA DA ENGRENAGEM RENAN
                onProfileClick = { navController.navigate(Rotas.PROFILE) }
            )
        },
        bottomBar = {
            FutGuessBottomBar(
                rotaAtual = rotaAtual,
                aoNavegar = { novaRota ->
                    navController.navigate(novaRota) {
                        popUpTo(Rotas.HOME) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { paddingValues ->

        val alvo = viewModel.jogadorAlvo.value
        val tamanhoPalavra = alvo?.nome?.length ?: 5

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // MENSAGEM DE ERRO PRA EU DESCOBRIR O QUE ESTÁ DANDO ERRADO
            if (viewModel.mensagemErro.value != null) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.Red.copy(alpha = 0.7f)),
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                ) {
                    Text(
                        text = viewModel.mensagemErro.value!!,
                        color = Color.White,
                        modifier = Modifier.padding(8.dp),
                        fontSize = 12.sp
                    )
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                for (i in 0 until 6) {
                    val configuration = LocalConfiguration.current
                    val screenWidth = configuration.screenWidthDp.dp
                    val margemLateral = 32.dp
                    val espacoEntreBlocos = 4.dp
                    val espacosTotais = espacoEntreBlocos * (tamanhoPalavra - 1)
                    val larguraDisponivel = screenWidth - margemLateral - espacosTotais
                    val tamanhoCalculado = larguraDisponivel / tamanhoPalavra
                    val tamanhoBox = min(50.dp, tamanhoCalculado)
                    val tamanhoTexto = (tamanhoBox.value * 0.5).sp
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val palavra = when {
                            i < viewModel.tentativas.size -> viewModel.tentativas[i]
                            i == viewModel.tentativas.size -> viewModel.inputAtual.value
                            else -> ""
                        }

                        val estadosLinha = if (i < viewModel.tentativas.size && alvo != null) {
                            viewModel.calcularEstados(palavra, alvo.nome)
                        } else {
                            emptyList()
                        }

                        for (j in 0 until tamanhoPalavra) {
                            val letra = palavra.getOrNull(j)

                            val corFundo = if (i < viewModel.tentativas.size && alvo != null && letra != null) {
                                when (estadosLinha.getOrNull(j)) {
                                    EstadoLetra.CERTA -> CorCerta
                                    EstadoLetra.LUGAR_ERRADO -> CorLugarErrado
                                    EstadoLetra.NAO_EXISTE -> CorNaoExiste
                                    else -> CorPadrao
                                }
                            } else {
                                if (letra != null) CorNaoExiste.copy(alpha = 0.5f) else Color.Transparent
                            }

                            if (j > 0) Spacer(modifier = Modifier.width(espacoEntreBlocos))

                            Box(
                                modifier = Modifier
                                    .size(tamanhoBox)
                                    .background(corFundo, RoundedCornerShape(4.dp))
                                    .border(BorderStroke(2.dp, if(letra != null) Color.Gray else Color.DarkGray),
                                        RoundedCornerShape(4.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = letra?.toString() ?: "",
                                    fontSize = tamanhoTexto,
                                    fontWeight = FontWeight.Bold,
                                    color = TextoBranco
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            if (alvo != null) {
                Text(
                    text = "DICA: ${alvo.dica}",
                    color = Color.LightGray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            if (viewModel.statusJogo.value != StatusJogo.JOGANDO) {
                val msg = if (viewModel.statusJogo.value == StatusJogo.VITORIA) "VOCÊ VENCEU!" else "JOGO PERDIDO: ${alvo?.nome}"
                Button(onClick = { viewModel.iniciarNovoJogo() }, colors = ButtonDefaults.buttonColors(containerColor = CorCerta)) {
                    Text("$msg - JOGAR NOVAMENTE")
                }
            }

            TecladoVirtual(
                onLetraClick = { viewModel.adicionarLetra(it) },
                onApagar = { viewModel.apagarLetra() },
                onEnter = { viewModel.confirmarTentativa() }
            )
        }
    }
}
@Composable
fun TecladoVirtual(onLetraClick: (Char) -> Unit, onApagar: () -> Unit, onEnter: () -> Unit) {
    val linhas = listOf("QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        linhas.forEachIndexed { index, linha ->
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                if (index == 2) TeclaEspecial(texto = "ENTER", cor = CorCerta, onClick = onEnter)

                linha.forEach { char ->
                    Box(
                        modifier = Modifier
                            .width(32.dp)
                            .height(45.dp)
                            .background(Color.DarkGray, RoundedCornerShape(4.dp))
                            .clickable { onLetraClick(char) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(char.toString(), color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }

                if (index == 2) TeclaEspecial(texto = "⌫", cor = Color.Red, onClick = onApagar)
            }
        }
    }
}

@Composable
fun TeclaEspecial(texto: String, cor: Color, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .height(45.dp)
            .background(cor, RoundedCornerShape(4.dp))
            .clickable { onClick() }
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(texto, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}