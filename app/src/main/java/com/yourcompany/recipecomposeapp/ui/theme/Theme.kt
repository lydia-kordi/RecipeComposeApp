package com.yourcompany.recipecomposeapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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

object Dimens {

    // Отступы
    val PaddingXS = 4.dp
    val PaddingS = 8.dp
    val PaddingM = 16.dp
    val PaddingL = 24.dp

    // Высоты экранов / элементов
    val ScreenTopBarHeight = 56.dp
    val ScreenBottomBarHeight = 64.dp

    // Тени (elevation)
    val ElevationS = 2.dp
    val ElevationM = 4.dp
    val ElevationL = 8.dp

    // Размеры элементов
    val IconSize = 24.dp
    val ImageSize = 120.dp

    // Кнопки
    val ButtonHeight = 48.dp
    val ButtonPaddingHorizontal = 16.dp
    val ButtonPaddingVertical = 12.dp

    // Слайдер
    val SliderHeight = 4.dp
    val SliderThumbSize = 20.dp
}

@Composable
fun RecipesComposeAppTheme(
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