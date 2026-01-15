package br.com.fsamuel.futguess.utils

import android.util.Patterns

object ValidationUtil {
    // https://developer.android.com/develop/ui/compose/quick-guides/content/validate-input?hl=pt-br
    fun isEmailValido(email: String): Boolean {
        return email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isSenhaValida(senha: String): Boolean {
        return senha.length >= 6
    }
}