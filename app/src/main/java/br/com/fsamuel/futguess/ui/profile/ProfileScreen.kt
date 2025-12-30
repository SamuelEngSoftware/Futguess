package br.com.fsamuel.futguess.ui.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fsamuel.futguess.navigation.Rotas
import br.com.fsamuel.futguess.ui.components.CampoTexto
import br.com.fsamuel.futguess.ui.components.FutGuessBottomBar
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel) {

    val launcherGaleria = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { viewModel.fotoUri.value = it }
    }

    Scaffold(
        bottomBar = {
            FutGuessBottomBar(rotaAtual = Rotas.PROFILE, aoNavegar = { navController.navigate(it) })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Meu Perfil", fontSize = 24.sp, modifier = Modifier.padding(bottom = 24.dp))

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
                    .clickable { launcherGaleria.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (viewModel.fotoUri.value != null) {
                    Image(
                        painter = rememberAsyncImagePainter(viewModel.fotoUri.value),
                        contentDescription = "Foto Perfil",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(Icons.Default.Person, contentDescription = null, tint = Color.White, modifier = Modifier.size(60.dp))
                }
            }

            Text(
                text = "Toque na foto para alterar",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
            )

            CampoTexto(viewModel.nome.value, { viewModel.nome.value = it }, "Nome", Icons.Default.Person)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = viewModel.email.value,
                onValueChange = {},
                label = { Text("Email") },
                enabled = false,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { viewModel.salvarPerfil() },
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("SALVAR ALTERAÇÕES")
            }

            if (viewModel.mensagemSucesso.value) {
                Text("Perfil atualizado com sucesso!", color = Color(0xFF388E3C), modifier = Modifier.padding(top = 16.dp))
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    navController.navigate(Rotas.LOGIN) {
                        popUpTo(Rotas.HOME) { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("SAIR")
            }
        }
    }
}