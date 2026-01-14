package br.com.fsamuel.futguess.utils

import org.mindrot.jbcrypt.BCrypt

object SecurityUtil {
    fun hashSenha(senha: String): String {
        return BCrypt.hashpw(senha, BCrypt.gensalt())
    }
    fun verificarSenha(senhaDigitada: String, hashSalvo: String): Boolean {
        return try {
            BCrypt.checkpw(senhaDigitada, hashSalvo)
        } catch (e: Exception) {
            false
        }
    }
}