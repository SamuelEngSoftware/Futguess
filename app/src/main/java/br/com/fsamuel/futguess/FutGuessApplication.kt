package br.com.fsamuel.futguess

import android.app.Application
import br.com.fsamuel.futguess.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FutGuessApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FutGuessApplication)
            modules(appModule)
        }
    }
}