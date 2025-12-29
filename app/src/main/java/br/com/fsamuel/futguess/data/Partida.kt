package br.com.fsamuel.futguess.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabela_partidas")
data class Partida(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val jogadorSorteado: String,
    val ganhou: Boolean,
    val tentativasUsadas: Int,
    val dataHora: Long = System.currentTimeMillis()
)