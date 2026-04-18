package com.yourcompany.recipecomposeapp.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yourcompany.recipecomposeapp.core.ui.theme.RecipesAppTheme
import com.yourcompany.recipecomposeapp.ui.recipes.model.IngredientUiModel

@Composable
fun IngredientsList(
    ingredients: List<IngredientUiModel>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        ingredients.forEachIndexed { index, ingredient ->
            IngredientItem(ingredient = ingredient)

            if (index < ingredients.lastIndex) {
                HorizontalDivider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IngredientsListPreview() {
    RecipesAppTheme {
        IngredientsList(
            ingredients = listOf(
                IngredientUiModel(
                    name = "говяжий фарш",
                    quantity = "0.5",
                    unitOfMeasure = "кг"
                ),
                IngredientUiModel(
                    name = "луковица, мелко нарезанная",
                    quantity = "1.0",
                    unitOfMeasure = "шт"
                ),
                IngredientUiModel(
                    name = "соль и черный перец",
                    quantity = "по вкусу",
                    unitOfMeasure = ""
                )
            )
        )
    }
}