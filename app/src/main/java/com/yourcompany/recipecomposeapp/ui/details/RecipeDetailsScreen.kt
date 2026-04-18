package com.yourcompany.recipecomposeapp.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.rememberAsyncImagePainter
import com.yourcompany.recipecomposeapp.core.ui.ScreenHeader
import com.yourcompany.recipecomposeapp.core.ui.theme.Dimens
import com.yourcompany.recipecomposeapp.core.ui.theme.RecipesAppTheme
import com.yourcompany.recipecomposeapp.data.repository.RecipesRepositoryStub
import com.yourcompany.recipecomposeapp.ui.recipes.model.RecipeUiModel
import com.yourcompany.recipecomposeapp.ui.recipes.model.toUiModel

@Composable
fun RecipeDetailsScreen(
    recipeId: Int,
    modifier: Modifier = Modifier
) {
    val recipe: RecipeUiModel? = RecipesRepositoryStub
        .getRecipeById(recipeId)
        ?.toUiModel()

    if (recipe == null) {
        Column(
            modifier = modifier.padding(Dimens.PaddingM)
        ) {
            Text(text = "Рецепт не найден")
        }
        return
    }

    val headerPainter = rememberAsyncImagePainter(
        model = recipe.imageUrl
    )

    var currentPortions by remember { mutableIntStateOf(2) }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        ScreenHeader(
            imagePainter = headerPainter,
            contentDescription = recipe.title,
            title = recipe.title.uppercase()
        )

        Column(
            modifier = Modifier.padding(
                start = Dimens.PaddingM,
                end = Dimens.PaddingM,
                top = Dimens.PaddingM,
                bottom = Dimens.PaddingS
            )
        ) {
            Text(
                text = "Ингредиенты".uppercase(),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Порции: $currentPortions",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(
                    horizontal = Dimens.PaddingS,
                    vertical = Dimens.PaddingS
                ),
            )

            PortionsSlider(
                currentPortions = currentPortions,
                onPortionsChange = { newValue ->
                    currentPortions = newValue
                }
            )

        }

        IngredientsList(
            ingredients = recipe.ingredients,
            modifier = Modifier.padding(
                start = Dimens.PaddingM,
                end = Dimens.PaddingM,
                top = Dimens.PaddingM,
                bottom = Dimens.PaddingS
            )
        )

        InstructionsList(
            instructions = recipe.method,
            modifier = Modifier.padding(
                start = Dimens.PaddingM,
                end = Dimens.PaddingM,
                top = Dimens.PaddingM,
                bottom = Dimens.PaddingS
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailsScreenPreview() {
    RecipesAppTheme {
        RecipeDetailsScreen(recipeId = 0)
    }
}