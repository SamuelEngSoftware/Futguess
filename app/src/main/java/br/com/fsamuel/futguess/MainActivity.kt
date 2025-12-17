package br.com.fsamuel.futguess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import br.com.fsamuel.futguess.data.AppDatabase
import br.com.fsamuel.futguess.navigation.GrafoNavegacao
import br.com.fsamuel.futguess.ui.theme.FutguessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()  Removi por enquanto pra não ficar atrás da barra de status

        setContent {
            FutguessTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val context = LocalContext.current
                    val db = AppDatabase.getDatabase(context)

                    val usuarioDao = db.usuarioDao()
                    val partidaDao = db.partidaDao()

                    GrafoNavegacao(usuarioDao = usuarioDao, partidaDao = partidaDao)
                }
            }
        }
    }
}
