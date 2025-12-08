package br.com.fsamuel.futguess.ui.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fsamuel.futguess.ui.theme.*
import br.com.fsamuel.futguess.ui.components.CampoTexto

@Composable
fun TelaLogin(
    viewModel: LoginViewModel,
    navegarParaCadastro: () -> Unit,
    navegarParaHome: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier.size(80.dp).clip(CircleShape).background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Star, "Logo", tint = VerdeBotao, modifier = Modifier.size(50.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "FutGuess", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
        Text(text = "Bem vindo de volta!", fontSize = 14.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f))

        Spacer(modifier = Modifier.height(32.dp))

        CampoTexto(viewModel.email.value, { viewModel.email.value = it }, "Email", Icons.Default.Email)
        Spacer(modifier = Modifier.height(16.dp))
        CampoTexto(viewModel.senha.value, { viewModel.senha.value = it }, "Senha", Icons.Default.Lock, isSenha = true)

        if (viewModel.erroLogin.value) {
            Text(
                text = "Email ou senha inválidos!",
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.fazerLogin { navegarParaHome() } },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = VerdeBotao),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "ENTRAR", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "NÃO TEM CONTA? CRIAR CONTA",
            color = VerdeBotao,
            fontSize = 12.sp,
            modifier = Modifier.clickable { navegarParaCadastro() }
        )
    }
}