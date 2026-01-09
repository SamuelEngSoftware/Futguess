package br.com.fsamuel.futguess.ui.history

import androidx.lifecycle.ViewModel
import br.com.fsamuel.futguess.data.Partida
import br.com.fsamuel.futguess.data.PartidaDao
import br.com.fsamuel.futguess.data.UserSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class HistoryViewModel(private val dao: PartidaDao) : ViewModel() {
    val usuarioId = UserSession.usuarioLogado?.id ?: -1
    val historico: Flow<List<Partida>> = if (usuarioId != -1) {
        dao.listarPartidas(usuarioId)
    } else {
        flowOf(emptyList())
    }
}