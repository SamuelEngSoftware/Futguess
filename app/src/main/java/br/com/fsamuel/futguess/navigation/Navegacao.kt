package br.com.fsamuel.futguess.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fsamuel.futguess.data.UsuarioDao
import br.com.fsamuel.futguess.ui.auth.cadastro.CadastroViewModel
import br.com.fsamuel.futguess.ui.auth.login.LoginViewModel
import br.com.fsamuel.futguess.ui.auth.cadastro.TelaCadastro
import br.com.fsamuel.futguess.ui.auth.login.TelaLogin
@Composable
fun GrafoNavegacao(dao: UsuarioDao) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Rotas.LOGIN) {

        composable(Rotas.LOGIN) {
            val loginViewModel: LoginViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return LoginViewModel(dao) as T
                    }
                }
            )

            TelaLogin(
                viewModel = loginViewModel,
                navegarParaCadastro = { navController.navigate(Rotas.CADASTRO) },
                navegarParaHome = {
                    navController.navigate(Rotas.HOME) {
                        popUpTo(Rotas.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(Rotas.CADASTRO) {
            val cadastroViewModel: CadastroViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return CadastroViewModel(dao) as T
                    }
                }
            )

            TelaCadastro(
                viewModel = cadastroViewModel,
                navegarParaLogin = { navController.popBackStack() }
            )
        }

        composable(Rotas.HOME) {
            br.com.fsamuel.futguess.ui.game.JogoScreen(navController = navController)
        }

        composable(Rotas.HISTORY) {
            androidx.compose.material3.Text("Tela de Histórico (Em construção)")
        }

        composable(Rotas.PROFILE) {
            androidx.compose.material3.Text("Tela de Perfil (Em construção)")
        }
    }
}