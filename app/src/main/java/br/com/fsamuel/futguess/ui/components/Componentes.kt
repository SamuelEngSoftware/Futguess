package br.com.fsamuel.futguess.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CampoTexto(
    valor: String,
    onChange: (String) -> Unit,
    label: String,
    icone: ImageVector,
    isSenha: Boolean = false
) {
    val containerColor = MaterialTheme.colorScheme.surfaceVariant
    val contentColor = MaterialTheme.colorScheme.onSurface

    OutlinedTextField(
        value = valor,
        onValueChange = onChange,
        label = { Text(label) },
        leadingIcon = { Icon(icone, contentDescription = null) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedTextColor = contentColor,
            unfocusedTextColor = contentColor,
            cursorColor = MaterialTheme.colorScheme.primary
        ),
        visualTransformation = if (isSenha) PasswordVisualTransformation() else VisualTransformation.None
    )
}