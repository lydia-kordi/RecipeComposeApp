package com.yourcompany.recipecomposeapp.ui.details

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yourcompany.recipecomposeapp.core.ui.theme.Dimens
import com.yourcompany.recipecomposeapp.core.ui.theme.RecipesAppTheme
import com.yourcompany.recipecomposeapp.ui.recipes.model.IngredientUiModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme

@Composable
fun IngredientItem(
    ingredient: IngredientUiModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = Dimens.PaddingS,
                vertical = Dimens.PaddingXS
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = ingredient.name.uppercase(),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = formatIngredientQuantity(
                quantity = ingredient.quantity,
                unitOfMeasure = ingredient.unitOfMeasure
            ).uppercase(),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = Dimens.PaddingS)
        )
    }
}

private fun formatIngredientQuantity(quantity: Double?, unitOfMeasure: String): String {
    if (quantity == null) return "по вкусу"

    val whole = quantity.toInt()
    val fraction = quantity - whole

    val formattedQuantity = when {
        fraction >= 0.75 -> {
            if (whole > 0) "$whole 3/4" else "3/4"
        }
        fraction >= 0.5 -> {
            if (whole > 0) "$whole 1/2" else "1/2"
        }
        fraction >= 0.25 -> {
            if (whole > 0) "$whole 1/4" else "1/4"
        }
        fraction > 0.0 -> "щепотка"
        else -> whole.toString()
    }

    return if (unitOfMeasure.isNotBlank()) {
        "$formattedQuantity $unitOfMeasure"
    } else {
        formattedQuantity
    }
}

@Preview(showBackground = true)
@Composable
fun IngredientItemPreview() {
    RecipesAppTheme {
        IngredientItem(
            ingredient = IngredientUiModel(
                name = "говяжий фарш",
                quantity = 0.5,
                unitOfMeasure = "кг"
            )
        )
    }
}