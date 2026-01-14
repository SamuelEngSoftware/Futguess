package br.com.fsamuel.futguess.ui.profile

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fsamuel.futguess.data.UserSession
import br.com.fsamuel.futguess.data.UsuarioDao
import kotlinx.coroutines.launch

class ProfileViewModel(private val dao: UsuarioDao) : ViewModel() {

    var nome = mutableStateOf("")
    var email = mutableStateOf("")
    var fotoUri = mutableStateOf<Uri?>(null)
    var mensagemSucesso = mutableStateOf(false)

    init {
        carregarDados()
    }

    private fun carregarDados() {
        val usuario = UserSession.usuarioLogado
        if (usuario != null) {
            nome.value = usuario.nome
            email.value = usuario.email //
            if (usuario.fotoUri != null) {
                fotoUri.value = Uri.parse(usuario.fotoUri)
            }
        }
    }

    fun salvarPerfil() {
        val usuarioAtual = UserSession.usuarioLogado ?: return

        viewModelScope.launch {
            val usuarioAtualizado = usuarioAtual.copy(
                nome = nome.value,
                fotoUri = fotoUri.value?.toString()
            )
            dao.salvarUsuario(usuarioAtualizado)
            UserSession.usuarioLogado = usuarioAtualizado
            mensagemSucesso.value = true
        }
    }
}