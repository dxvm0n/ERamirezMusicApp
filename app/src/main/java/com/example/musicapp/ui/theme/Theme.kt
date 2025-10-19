package com.example.musicapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 🎨 Paleta personalizada
private val PurplePrimary = Color(0xFF7C58FF)
private val PurpleSecondary = Color(0xFF9B7BFF)
private val DarkSurface = Color(0xFF2A104D)
private val LightBackground = Color(0xFFF8F8FF)

private val LightColors = lightColorScheme(
    primary = PurplePrimary,
    secondary = PurpleSecondary,
    background = LightBackground,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onSurface = Color(0xFF1E1E1E),
    onBackground = Color(0xFF1E1E1E),
    surfaceVariant = Color(0xFFF4F2FF),
)

private val DarkColors = darkColorScheme(
    primary = PurplePrimary,
    secondary = PurpleSecondary,
    background = Color(0xFF1B0A3C),
    surface = Color(0xFF2A104D),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onSurface = Color.White,
    onBackground = Color.White,
    surfaceVariant = Color(0xFF3D256A)
)

@Composable
fun MusicAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
