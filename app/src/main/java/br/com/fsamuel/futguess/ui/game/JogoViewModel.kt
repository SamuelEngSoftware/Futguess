package br.com.fsamuel.futguess.ui.game

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fsamuel.futguess.data.remote.Jogador
import br.com.fsamuel.futguess.data.remote.RetrofitClient
import kotlinx.coroutines.launch

enum class EstadoLetra { CERTA, LUGAR_ERRADO, NAO_EXISTE}
enum class StatusJogo { JOGANDO, VITORIA, DERROTA }

class JogoViewModel : ViewModel() {

    var jogadorAlvo = mutableStateOf<Jogador?>(null)
    var tentativas = mutableStateListOf<String>()
    var inputAtual = mutableStateOf("")
    var statusJogo = mutableStateOf(StatusJogo.JOGANDO)
    var mensagemErro = mutableStateOf<String?>(null)

    init {
        iniciarNovoJogo()
    }

    fun iniciarNovoJogo() {
        viewModelScope.launch {
            try {
                mensagemErro.value = null

                val lista = RetrofitClient.api.buscarJogadores()

                if (lista.isNotEmpty()) {
                    val sorteado = lista.random()
                    jogadorAlvo.value = sorteado.copy(nome = sorteado.nome.uppercase())
                    tentativas.clear()
                    inputAtual.value = ""
                    statusJogo.value = StatusJogo.JOGANDO
                } else {
                    mensagemErro.value = "A lista de jogadores veio vazia!"
                }

            } catch (e: Exception) {
                mensagemErro.value = "Erro na API: ${e.message}"
                statusJogo.value = StatusJogo.DERROTA
            }
        }
    }

    fun adicionarLetra(letra: Char) {
        if (statusJogo.value != StatusJogo.JOGANDO) return
        val alvo = jogadorAlvo.value ?: return
        if (inputAtual.value.length < alvo.nome.length) {
            inputAtual.value += letra
        }
    }

    fun apagarLetra() {
        if (inputAtual.value.isNotEmpty()) {
            inputAtual.value = inputAtual.value.dropLast(1)
        }
    }

    fun confirmarTentativa() {
        val alvo = jogadorAlvo.value?.nome ?: return
        val chute = inputAtual.value

        if (chute.length != alvo.length) {
            mensagemErro.value = "Complete a palavra!"
            return
        }

        tentativas.add(chute)
        inputAtual.value = ""
        mensagemErro.value = null

        if (chute == alvo) {
            statusJogo.value = StatusJogo.VITORIA
        } else if (tentativas.size >= 6) {
            statusJogo.value = StatusJogo.DERROTA
        }
    }

    fun verificarCor(letra: Char, index: Int, palavraCorreta: String): EstadoLetra {
        return when {
            palavraCorreta[index] == letra -> EstadoLetra.CERTA
            palavraCorreta.contains(letra) -> EstadoLetra.LUGAR_ERRADO
            else -> EstadoLetra.NAO_EXISTE
        }
    }
}