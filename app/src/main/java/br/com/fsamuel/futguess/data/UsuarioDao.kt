package br.com.fsamuel.futguess.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvarUsuario(usuario: Usuario)
    @Query("SELECT * FROM tabela_usuarios WHERE email = :email LIMIT 1")
    suspend fun buscarPorEmail(email: String): Usuario?

    @Query("UPDATE tabela_usuarios SET senha = :novaSenha WHERE email = :email")
    suspend fun atualizarSenha(email: String, novaSenha: String)
}