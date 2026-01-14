package br.com.fsamuel.futguess.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsuarioDao {
    @Insert
    suspend fun salvarUsuario(usuario: Usuario)
    @Update
    suspend fun atualizarUsuario(usuario: Usuario)
    @Update
    suspend fun atualizarMoeda(usuario: Usuario)
    @Query("SELECT * FROM tabela_usuarios WHERE email = :email LIMIT 1")
    suspend fun buscarPorEmail(email: String): Usuario?

    @Query("UPDATE tabela_usuarios SET senha = :novaSenha WHERE email = :email")
    suspend fun atualizarSenha(email: String, novaSenha: String)
}