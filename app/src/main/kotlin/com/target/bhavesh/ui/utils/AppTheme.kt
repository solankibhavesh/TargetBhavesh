package com.target.bhavesh.ui.utils

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val DarkColorScheme = darkColorScheme(
    background = ColorBackgroundDark,
    primary = ColorOnSurfaceDark,
    secondary = ColorSecondaryDark,
    primaryContainer = Color.Black
)
val LightColorScheme = lightColorScheme(
    background = ColorBackgroundLight,
    primary = ColorOnSurfaceLight,
    secondary = ColorSecondaryLight,
    primaryContainer = Color.White
)

@Composable
fun AppMaterialTheme(
    isThemeDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {

    val colorScheme = when {
        isThemeDark -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = ColorPrimary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isThemeDark
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
