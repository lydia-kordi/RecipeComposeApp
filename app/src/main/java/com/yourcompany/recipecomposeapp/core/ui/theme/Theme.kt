package com.yourcompany.recipecomposeapp.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val RecipesAppLightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = Color.White,

    tertiary = AccentBlue,
    onTertiary = Color.White,

    tertiaryContainer = SliderTrackColor,

    error = AccentColor,
    onError = Color.White,

    background = BackgroundColor,
    onBackground = TextPrimaryColor,

    surface = SurfaceColor,
    onSurface = TextPrimaryColor,

    outline = DividerColor,

    surfaceVariant = SurfaceVariantColor,
    onSurfaceVariant = TextSecondaryColor,

)

private val RecipesAppDarkColorScheme = darkColorScheme(
    primary = PrimaryColorDark,
    onPrimary = Color.Black,

    tertiary = AccentBlueDark,
    onTertiary = Color.Black,

    tertiaryContainer = SliderTrackColorDark,

    error = AccentColorDark,
    onError = Color.Black,

    background = BackgroundColorDark,
    onBackground = TextPrimaryColorDark,

    surface = SurfaceColorDark,
    onSurface = TextPrimaryColorDark,

    surfaceVariant = SurfaceVariantColorDark,
    onSurfaceVariant = TextSecondaryColorDark,

    outline = DividerColorDark,
)

@Composable
fun RecipesAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> RecipesAppDarkColorScheme
        else -> RecipesAppLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = recipesAppTypography,
        content = content
    )
}