package br.com.fsamuel.futguess.ui.auth.esqueceusenha

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fsamuel.futguess.ui.theme.VerdeBotao
import br.com.fsamuel.futguess.ui.components.CampoTexto
import org.koin.androidx.compose.koinViewModel

@Composable
fun EsqueceuSenhaScreen(
    onNavigateBack: () -> Unit,
    viewModel: EsqueceuSenhaViewModel = koinViewModel()
) {
    var mostrarDialogSucesso by remember { mutableStateOf(false) }

    if (mostrarDialogSucesso) {
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                Button(
                    onClick = { onNavigateBack() },
                    colors = ButtonDefaults.buttonColors(containerColor = VerdeBotao)
                ) {
                    Text("Fazer Login")
                }
            },
            title = { Text("Sucesso!") },
            text = { Text("Sua senha foi redefinida com sucesso.") }
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Recuperar Senha",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(32.dp))

        CampoTexto(
            viewModel.email.value,
            { viewModel.email.value = it },
            "Email",
            Icons.Default.Email
        )

        Spacer(modifier = Modifier.height(16.dp))

        CampoTexto(
            viewModel.novaSenha.value,
            { viewModel.novaSenha.value = it },
            "Nova Senha",
            Icons.Default.Lock,
            isSenha = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        CampoTexto(
            viewModel.confirmarSenha.value,
            { viewModel.confirmarSenha.value = it },
            "Confirmar Senha",
            Icons.Default.Lock,
            isSenha = true
        )

        if (viewModel.mensagemErro.value != null) {
            Text(
                text = viewModel.mensagemErro.value!!,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.redefinirSenha(aoSucesso = { mostrarDialogSucesso = true })
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = VerdeBotao),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "REDEFINIR SENHA", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        TextButton(onClick = { onNavigateBack() }) {
            Text("Cancelar / Voltar", color = Color.Gray)
        }
    }
}