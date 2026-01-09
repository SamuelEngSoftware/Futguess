package br.com.fsamuel.futguess.di

import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import br.com.fsamuel.futguess.data.AppDatabase
import br.com.fsamuel.futguess.data.remote.RetrofitClient
import br.com.fsamuel.futguess.ui.auth.login.LoginViewModel
import br.com.fsamuel.futguess.ui.auth.cadastro.CadastroViewModel
import br.com.fsamuel.futguess.ui.game.JogoViewModel
import br.com.fsamuel.futguess.ui.history.HistoryViewModel
import br.com.fsamuel.futguess.ui.profile.ProfileViewModel

val appModule = module {

    single { AppDatabase.getDatabase(androidContext()) }

    single { get<AppDatabase>().usuarioDao() }
    single { get<AppDatabase>().partidaDao() }

    single { RetrofitClient.api }

    viewModel { LoginViewModel(get()) }
    viewModel { CadastroViewModel(get()) }
    viewModel { JogoViewModel(get(), get()) }
    viewModel { HistoryViewModel(get()) }
    viewModel { ProfileViewModel(get()) }


}