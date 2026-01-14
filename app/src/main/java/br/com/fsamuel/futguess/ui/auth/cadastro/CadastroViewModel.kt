package br.com.fsamuel.futguess.ui.auth.cadastro

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteQuery
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fsamuel.futguess.data.Usuario
import br.com.fsamuel.futguess.data.UsuarioDao
import kotlinx.coroutines.launch
import br.com.fsamuel.futguess.utils.SecurityUtil
import java.sql.SQLXML

class CadastroViewModel(private val dao: UsuarioDao) : ViewModel() {

    var nome = mutableStateOf("")
    var email = mutableStateOf("")
    var senha = mutableStateOf("")

    var mensagemErro = mutableStateOf<String?>(null)

    fun criarConta(aoFinalizar: () -> Unit) {
        if (nome.value.isNotEmpty() && email.value.isNotEmpty() && senha.value.isNotEmpty()) {
            viewModelScope.launch {
                try {
                    val novaSenhaHash = SecurityUtil.hashSenha(senha.value)
                    val novoUsuario = Usuario(
                        nome = nome.value,
                        email = email.value,
                        senha = novaSenhaHash
                    )
                    dao.salvarUsuario(novoUsuario)
                    aoFinalizar()
                } catch (e: SQLiteConstraintException) {
                    mensagemErro.value = "Este e-mail já está em uso"
                } catch (e: Exception) {
                    mensagemErro.value = "Erro técnico: ${e.message}"
                }
            }
        } else {
            mensagemErro.value = "Preencha todos os campos!"
        }
    }
}