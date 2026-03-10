package com.example.cashregister.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val CashRegisterColorScheme = lightColorScheme(
    primary = BlueSlate,
    onPrimary = Color.White,
    secondary = PowderBlush,
    background = VanillaCream,
    surface = VanillaCream
)

@Composable
fun CashRegisterTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = CashRegisterColorScheme,
        content = content
    )
}
