package br.com.fsamuel.futguess.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PartidaDao {
    @Insert
    suspend fun salvarPartida(partida: Partida)

    @Query("SELECT * FROM tabela_partidas ORDER BY dataHora DESC")
    fun listarPartidas(): Flow<List<Partida>>
}