package br.com.fsamuel.futguess.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.fsamuel.futguess.data.Partida
import br.com.fsamuel.futguess.data.PartidaDao
import kotlinx.coroutines.flow.Flow

class HistoryViewModel(private val dao: PartidaDao) : ViewModel() {

    val historico: Flow<List<Partida>> = dao.listarPartidas()

    class Factory(private val dao: PartidaDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
                return HistoryViewModel(dao) as T
            }
            throw IllegalArgumentException("Classe ViewModel desconhecida")
        }
    }
}