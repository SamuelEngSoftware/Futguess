package br.com.fsamuel.futguess.ui.auth.cadastro

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fsamuel.futguess.data.Usuario
import br.com.fsamuel.futguess.data.UsuarioDao
import kotlinx.coroutines.launch

class CadastroViewModel(private val dao: UsuarioDao) : ViewModel() {

    var nome = mutableStateOf("")
    var email = mutableStateOf("")
    var senha = mutableStateOf("")

    fun criarConta(aoFinalizar: () -> Unit) {
        if (nome.value.isNotEmpty() && email.value.isNotEmpty() && senha.value.isNotEmpty()) {
            viewModelScope.launch {
                val novoUsuario = Usuario(
                    nome = nome.value,
                    email = email.value,
                    senha = senha.value
                )
                dao.salvarUsuario(novoUsuario)
                aoFinalizar()
            }
        }
    }
}