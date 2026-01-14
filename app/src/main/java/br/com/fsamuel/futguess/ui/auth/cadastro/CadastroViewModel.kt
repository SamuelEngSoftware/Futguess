package br.com.fsamuel.futguess.ui.auth.cadastro

import android.database.sqlite.SQLiteConstraintException
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fsamuel.futguess.data.Usuario
import br.com.fsamuel.futguess.data.UsuarioDao
import br.com.fsamuel.futguess.utils.SecurityUtil
import br.com.fsamuel.futguess.utils.ValidationUtil
import kotlinx.coroutines.launch

class CadastroViewModel(private val dao: UsuarioDao) : ViewModel() {

    var nome = mutableStateOf("")
    var email = mutableStateOf("")
    var senha = mutableStateOf("")

    var mensagemErro = mutableStateOf<String?>(null)

    fun criarConta(aoFinalizar: () -> Unit) {
        mensagemErro.value = null

        if (nome.value.isBlank() || email.value.isBlank() || senha.value.isBlank()) {
            mensagemErro.value = "Preencha todos os campos!"
            return
        }

        if (!ValidationUtil.isEmailValido(email.value)) {
            mensagemErro.value = "E-mail inválido! Verifique o formato."
            return
        }

        if (!ValidationUtil.isSenhaValida(senha.value)) {
            mensagemErro.value = "A senha deve ter pelo menos 6 caracteres."
            return
        }

        viewModelScope.launch {
            try {
                val novaSenhaHash = SecurityUtil.hashSenha(senha.value)

                val novoUsuario = Usuario(
                    nome = nome.value,
                    email = email.value,
                    senha = novaSenhaHash // Salva o Hash
                )

                dao.salvarUsuario(novoUsuario)
                aoFinalizar()

            } catch (e: SQLiteConstraintException) {
                    mensagemErro.value = "Este e-mail já está em uso."
                }
            }
        }
    }