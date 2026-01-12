package br.com.fsamuel.futguess.ui.auth.esqueceusenha

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fsamuel.futguess.data.UsuarioDao
import kotlinx.coroutines.launch

class EsqueceuSenhaViewModel(private val dao: UsuarioDao) : ViewModel() {

    var email = mutableStateOf("")
    var novaSenha = mutableStateOf("")
    var confirmarSenha = mutableStateOf("")
    var mensagemErro = mutableStateOf<String?>(null)
    fun redefinirSenha(aoSucesso: () -> Unit) {
        if (email.value.isEmpty() || novaSenha.value.isEmpty() || confirmarSenha.value.isEmpty()) {
            mensagemErro.value = "Preencha todos os campos."
            return
        }
        if (novaSenha.value != confirmarSenha.value) {
            mensagemErro.value = "As senhas não coincidem."
            return
        }

        viewModelScope.launch {
            try {
                val usuario = dao.buscarPorEmail(email.value)
                if (usuario != null) {
                    dao.atualizarSenha(email.value, novaSenha.value)
                    aoSucesso()
                } else {
                    mensagemErro.value = "E-mail não encontrado."
                }
            } catch (e: Exception) {
                mensagemErro.value = "Erro: ${e.message}"
            }
        }
    }
}