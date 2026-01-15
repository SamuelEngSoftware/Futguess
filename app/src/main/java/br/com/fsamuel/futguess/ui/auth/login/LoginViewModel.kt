package br.com.fsamuel.futguess.ui.auth.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fsamuel.futguess.data.UserSession
import br.com.fsamuel.futguess.data.repository.UsuarioRepository
import br.com.fsamuel.futguess.utils.SecurityUtil
import kotlinx.coroutines.launch

class LoginViewModel(private val usuarioRepository: UsuarioRepository) : ViewModel() {

    var email = mutableStateOf("")
    var senha = mutableStateOf("")
    var erroLogin = mutableStateOf(false)

    fun fazerLogin(aoLogar: () -> Unit) {

        if (email.value.isBlank() || senha.value.isBlank()) {
            erroLogin.value = true
            return
        }

        viewModelScope.launch {
            val usuarioEncontrado = usuarioRepository.buscarPorEmail(email.value)

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