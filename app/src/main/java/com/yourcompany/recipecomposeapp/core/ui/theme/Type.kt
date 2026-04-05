package com.yourcompany.recipecomposeapp.core.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

val recipesAppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = montserratAlternatesFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = Dimens.TextSizeDisplayLarge,
        lineHeight = Dimens.LineHeightDisplayLarge,
    ),
    titleMedium = TextStyle(
        fontFamily = montserratAlternatesFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = Dimens.TextSizeTitleMedium,
        lineHeight = Dimens.LineHeightTitleMedium,
    ),
    bodyMedium = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = Dimens.TextSizeBodyMedium,
        lineHeight = Dimens.LineHeightBodyMedium,
    ),
    bodySmall = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = Dimens.TextSizeBodySmall,
        lineHeight = Dimens.LineHeightBodySmall,
    ),
    labelLarge = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = Dimens.TextSizeLabelLarge,
        lineHeight = Dimens.LineHeightLabelLarge,
    )
)

@Preview(showBackground = true)
@Composable
fun TypographyPreview() {
    RecipesAppTheme {
        Column(
            modifier = Modifier.padding(Dimens.PaddingM),
            verticalArrangement = Arrangement.spacedBy(Dimens.PaddingS)
        ) {
            Text(
                "displayLarge - Заголовки экранов",
                style = MaterialTheme.typography.displayLarge
            )
            Text(
                "titleMedium - Карточки",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                "bodyMedium - Основной текст",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                "bodySmall - Мелкий текст",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "labelLarge - Кнопки",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}