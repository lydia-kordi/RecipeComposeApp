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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.platform.LocalContext
import com.yourcompany.recipecomposeapp.core.utils.FavoriteDataStoreManager
import com.yourcompany.recipecomposeapp.core.utils.shareRecipe
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RecipeDetailsScreen(
    recipe: RecipeUiModel,
    modifier: Modifier = Modifier
) {

    val headerPainter = rememberAsyncImagePainter(
        model = recipe.imageUrl
    )

    var currentPortions by rememberSaveable(recipe.id) { mutableIntStateOf(recipe.servings) }

    val context = LocalContext.current
    val favoriteDataStoreManager = remember(context) {
        FavoriteDataStoreManager(context)
    }
    val coroutineScope = rememberCoroutineScope()

    var isFavorite by rememberSaveable(recipe.id) {
        mutableStateOf(false)
    }

    LaunchedEffect(recipe.id) {
        isFavorite = favoriteDataStoreManager.isFavorite(recipe.id)
    }

    val scaledIngredients = remember(currentPortions, recipe.ingredients) {
        val multiplier = currentPortions.toDouble() / recipe.servings

        recipe.ingredients.map { ingredient ->
            ingredient.copy(
                quantity = ingredient.quantity?.times(multiplier)
            )
        }
    }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        ScreenHeader(
            imagePainter = headerPainter,
            contentDescription = recipe.title,
            title = recipe.title.uppercase(),
            showFavoriteButton = true,
            isFavorite = isFavorite,
            onFavoriteToggle = {
                coroutineScope.launch {
                    if (isFavorite) {
                        favoriteDataStoreManager.removeFavorite(recipe.id)
                    } else {
                        favoriteDataStoreManager.addFavorite(recipe.id)
                    }
                    isFavorite = !isFavorite
                }
            },
            showShareButton = true,
            onShareClick = {
                shareRecipe(context, recipe.id, recipe.title)
            }
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
                style = MaterialTheme.typography.displayLarge,
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
            ingredients = scaledIngredients,
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
    val recipe = RecipesRepositoryStub
        .getRecipeById(0)
        ?.toUiModel()

    RecipesAppTheme {
        recipe?.let {
            RecipeDetailsScreen(recipe = it)
        }
    }
}