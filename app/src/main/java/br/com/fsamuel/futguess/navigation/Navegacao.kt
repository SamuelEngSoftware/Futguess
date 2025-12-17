package br.com.fsamuel.futguess.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fsamuel.futguess.data.PartidaDao
import br.com.fsamuel.futguess.data.UsuarioDao
import br.com.fsamuel.futguess.ui.auth.cadastro.CadastroViewModel
import br.com.fsamuel.futguess.ui.auth.login.LoginViewModel
import br.com.fsamuel.futguess.ui.auth.cadastro.TelaCadastro
import br.com.fsamuel.futguess.ui.auth.login.TelaLogin
import br.com.fsamuel.futguess.ui.game.JogoScreen
import br.com.fsamuel.futguess.ui.game.JogoViewModel
import br.com.fsamuel.futguess.ui.history.HistoryScreen
import br.com.fsamuel.futguess.ui.history.HistoryViewModel

@Composable
fun GrafoNavegacao(usuarioDao: UsuarioDao, partidaDao: PartidaDao) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Rotas.LOGIN) {

        composable(Rotas.LOGIN) {
            val loginViewModel: LoginViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return LoginViewModel(usuarioDao) as T
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
                        return CadastroViewModel(usuarioDao) as T
                    }
                }
            )

            TelaCadastro(
                viewModel = cadastroViewModel,
                navegarParaLogin = { navController.popBackStack() }
            )
        }

        composable(Rotas.HOME) {
            val jogoViewModel: JogoViewModel = viewModel(
                factory = JogoViewModel.Factory(partidaDao)
            )
            JogoScreen(navController = navController, viewModel = jogoViewModel)
        }

        composable(Rotas.HISTORY) {
            val historyViewModel: HistoryViewModel = viewModel(
                factory = HistoryViewModel.Factory(partidaDao)
            )
            HistoryScreen(navController = navController, viewModel = historyViewModel)
        }

        composable(Rotas.PROFILE) {
            androidx.compose.material3.Text("Tela de Perfil (Em construção)")
        }


        composable(Rotas.PROFILE) {
            androidx.compose.material3.Text("Tela de Perfil (Em construção)")
        }
    }
}