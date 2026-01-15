package br.com.fsamuel.futguess.data.repository

import br.com.fsamuel.futguess.data.Partida
import br.com.fsamuel.futguess.data.PartidaDao
import br.com.fsamuel.futguess.data.remote.FutebolApi
import br.com.fsamuel.futguess.data.remote.Jogador
import kotlinx.coroutines.flow.Flow

class JogoRepository(
    private val api: FutebolApi,
    private val partidaDao: PartidaDao
) {
    suspend fun buscarJogadoresApi(): List<Jogador> {
        return try {
            api.buscarJogadores()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun salvarPartida(partida: Partida) = partidaDao.salvarPartida(partida)

    fun listarHistorico(usuarioId: Int): Flow<List<Partida>> = partidaDao.listarPartidas(usuarioId)
}