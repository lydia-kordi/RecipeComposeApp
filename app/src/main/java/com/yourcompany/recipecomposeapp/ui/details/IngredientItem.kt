package com.yourcompany.recipecomposeapp.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yourcompany.recipecomposeapp.core.ui.theme.Dimens
import com.yourcompany.recipecomposeapp.core.ui.theme.RecipesAppTheme
import com.yourcompany.recipecomposeapp.ui.recipes.model.IngredientUiModel

@Composable
fun IngredientItem(
    ingredient: IngredientUiModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.PaddingS)
    ) {
        Text(text = ingredient.name)
        Text(
            text = listOf(ingredient.quantity, ingredient.unitOfMeasure)
                .filter { it.isNotBlank() }
                .joinToString(" ")
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IngredientItemPreview() {
    RecipesAppTheme {
        IngredientItem(
            ingredient = IngredientUiModel(
                name = "говяжий фарш",
                quantity = "0.5",
                unitOfMeasure = ""
            )
        )
    }
}