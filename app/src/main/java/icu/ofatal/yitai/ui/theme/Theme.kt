package icu.ofatal.yitai.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import icu.ofatal.yitai.ui.local.LocalDarkMode
import icu.ofatal.yitai.ui.local.LocalWindowSizeClass
import icu.ofatal.yitai.util.findActivity
import me.rerere.md3compat.Md3CompatTheme

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200
    /* Other default colors to override
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun YitaiTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val context = LocalContext.current
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val windowClass = calculateWindowSizeClass(context.findActivity())

    CompositionLocalProvider(
        LocalDarkMode provides darkTheme,
        LocalWindowSizeClass provides windowClass
    ) {
        ApplyBarColor()
        Md3CompatTheme {
            MaterialTheme(
                colors = colors,
                typography = Typography,
                shapes = Shapes,
                content = content,
            )
        }
    }

}

@Composable
fun ApplyBarColor(darkTheme: Boolean = LocalDarkMode.current, color: Color =  Color.Transparent) {
    val view = LocalView.current
    val activity = LocalContext.current as Activity
    SideEffect {
        view.context.findActivity().window.apply {
            statusBarColor = color.toArgb()
            navigationBarColor = color.toArgb()
        }
        WindowCompat.getInsetsController(activity.window,view).apply {
            isAppearanceLightNavigationBars = !darkTheme
            isAppearanceLightStatusBars = !darkTheme
        }
    }
}