package br.com.fsamuel.futguess.data.repository

import br.com.fsamuel.futguess.data.Usuario
import br.com.fsamuel.futguess.data.UsuarioDao

class UsuarioRepository(private val dao: UsuarioDao) {
    suspend fun salvarUsuario(usuario: Usuario) = dao.salvarUsuario(usuario)

    suspend fun buscarPorEmail(email: String): Usuario? = dao.buscarPorEmail(email)

    suspend fun atualizarUsuario(usuario: Usuario) = dao.atualizarUsuario(usuario)

    suspend fun atualizarSenha(email: String, novaSenhaHash: String) = dao.atualizarSenha(email, novaSenhaHash)

    suspend fun atualizarMoedas(usuario: Usuario) = dao.atualizarMoeda(usuario)
}