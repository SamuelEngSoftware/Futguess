package br.com.fsamuel.futguess.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import  androidx.room.Index

@Entity(tableName = "tabela_usuarios",
        indices = [Index(value = ["email"], unique = true)]
)
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val email: String,
    val senha: String,
    val fotoUri: String? = null,
    val moedas: Int = 0
)