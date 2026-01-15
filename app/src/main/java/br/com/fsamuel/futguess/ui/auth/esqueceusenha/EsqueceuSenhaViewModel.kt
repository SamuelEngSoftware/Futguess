package br.com.fsamuel.futguess.ui.auth.esqueceusenha

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fsamuel.futguess.data.repository.UsuarioRepository
import kotlinx.coroutines.launch
import br.com.fsamuel.futguess.utils.SecurityUtil
import br.com.fsamuel.futguess.utils.ValidationUtil

class EsqueceuSenhaViewModel(private val usuarioRepository: UsuarioRepository) : ViewModel() {

    var email = mutableStateOf("")
    var novaSenha = mutableStateOf("")
    var confirmarSenha = mutableStateOf("")
    var mensagemErro = mutableStateOf<String?>(null)
    fun redefinirSenha(aoSucesso: () -> Unit) {
        mensagemErro.value = null

        if (email.value.isBlank() || novaSenha.value.isBlank() || confirmarSenha.value.isBlank()) {
            mensagemErro.value = "Preencha todos os campos."
            return
        }

        if (!ValidationUtil.isEmailValido(email.value)) {
            mensagemErro.value = "E-mail inválido."
            return
        }

        if (!ValidationUtil.isSenhaValida(novaSenha.value)) {
            mensagemErro.value = "A nova senha deve ter pelo menos 6 caracteres."
            return
        }

        if (novaSenha.value != confirmarSenha.value) {
            mensagemErro.value = "As senhas não coincidem."
            return
        }

        viewModelScope.launch {
            val usuario = usuarioRepository.buscarPorEmail(email.value)
            if (usuario != null) {
                val novaSenhaHash = SecurityUtil.hashSenha(novaSenha.value)
                usuarioRepository.atualizarSenha(email.value, novaSenhaHash)
                aoSucesso()
            } else {
                mensagemErro.value = "E-mail não encontrado."
            }
        }
    }
}