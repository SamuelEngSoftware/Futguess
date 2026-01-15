package br.com.fsamuel.futguess.ui.history

import androidx.lifecycle.ViewModel
import br.com.fsamuel.futguess.data.Partida
import br.com.fsamuel.futguess.data.UserSession
import br.com.fsamuel.futguess.data.repository.JogoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class HistoryViewModel(private val jogoRepository: JogoRepository) : ViewModel() {
    val usuarioId = UserSession.usuarioLogado?.id ?: -1
    val historico: Flow<List<Partida>> = if (usuarioId != -1) {
        jogoRepository.listarHistorico(usuarioId)
    } else {
        flowOf(emptyList())
    }
}