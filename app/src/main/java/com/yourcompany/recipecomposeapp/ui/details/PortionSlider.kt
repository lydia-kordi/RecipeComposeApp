package com.yourcompany.recipecomposeapp.ui.details

import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.yourcompany.recipecomposeapp.core.ui.theme.RecipesAppTheme
import kotlin.math.roundToInt
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.yourcompany.recipecomposeapp.core.ui.theme.Dimens

@Composable
fun PortionsSlider(
    currentPortions: Int,
    onPortionsChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Slider(
        value = currentPortions.toFloat(),
        onValueChange = { onPortionsChange(it.roundToInt()) },
        valueRange = 1f..12f,
        steps = 10,
        modifier = modifier.height(Dimens.SliderHeightM),
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.primary,
            activeTrackColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.45f),
            inactiveTrackColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.45f),
            activeTickColor = Color.Transparent,
            inactiveTickColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PortionsSliderPreview() {
    RecipesAppTheme {
        PortionsSlider(
            currentPortions = 4, onPortionsChange = {})
    }
}