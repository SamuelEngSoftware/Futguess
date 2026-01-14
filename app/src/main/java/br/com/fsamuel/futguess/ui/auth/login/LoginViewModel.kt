package br.com.fsamuel.futguess.ui.auth.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fsamuel.futguess.data.UserSession
import br.com.fsamuel.futguess.data.UsuarioDao
import br.com.fsamuel.futguess.utils.SecurityUtil
import kotlinx.coroutines.launch

class LoginViewModel(private val dao: UsuarioDao) : ViewModel() {

    var email = mutableStateOf("")
    var senha = mutableStateOf("")
    var erroLogin = mutableStateOf(false)

    fun fazerLogin(aoLogar: () -> Unit) {
        viewModelScope.launch {
            val usuarioEncontrado = dao.buscarPorEmail(email.value)
            if (usuarioEncontrado != null) {
                val senhaConfere = SecurityUtil.verificarSenha(
                    senhaDigitada = senha.value,
                    hashSalvo = usuarioEncontrado.senha
                )
                if (senhaConfere) {
                    UserSession.usuarioLogado = usuarioEncontrado
                    aoLogar()
                } else {
                    erroLogin.value = true
                }
            } else {
                erroLogin.value = true
            }
        }
    }
}