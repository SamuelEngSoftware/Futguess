package br.com.fsamuel.futguess.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fsamuel.futguess.ui.auth.cadastro.CadastroViewModel
import br.com.fsamuel.futguess.ui.auth.cadastro.TelaCadastro
import br.com.fsamuel.futguess.ui.auth.login.LoginViewModel
import br.com.fsamuel.futguess.ui.auth.login.TelaLogin
import br.com.fsamuel.futguess.ui.game.JogoScreen
import br.com.fsamuel.futguess.ui.game.JogoViewModel
import br.com.fsamuel.futguess.ui.history.HistoryScreen
import br.com.fsamuel.futguess.ui.history.HistoryViewModel
import br.com.fsamuel.futguess.ui.profile.ProfileScreen
import br.com.fsamuel.futguess.ui.profile.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun GrafoNavegacao() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Rotas.LOGIN) {

        composable(Rotas.LOGIN) {
            val loginViewModel: LoginViewModel = koinViewModel()

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
            val cadastroViewModel: CadastroViewModel = koinViewModel()

            TelaCadastro(
                viewModel = cadastroViewModel,
                navegarParaLogin = { navController.popBackStack() }
            )
        }

        composable(Rotas.HOME) {
            val jogoViewModel: JogoViewModel = koinViewModel()
            JogoScreen(navController = navController, viewModel = jogoViewModel)
        }

        composable(Rotas.HISTORY) {
            val historyViewModel: HistoryViewModel = koinViewModel()
            HistoryScreen(navController = navController, viewModel = historyViewModel)
        }

        composable(Rotas.PROFILE) {
            val profileViewModel: ProfileViewModel = koinViewModel()
            ProfileScreen(navController = navController, viewModel = profileViewModel)
        }

    }
}