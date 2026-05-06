package com.yourcompany.recipecomposeapp.ui.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.yourcompany.recipecomposeapp.R
import com.yourcompany.recipecomposeapp.core.ui.ScreenHeader
import com.yourcompany.recipecomposeapp.core.ui.theme.Dimens
import com.yourcompany.recipecomposeapp.core.ui.theme.RecipesAppTheme
import com.yourcompany.recipecomposeapp.core.utils.FavoriteDataStoreManager
import com.yourcompany.recipecomposeapp.data.repository.RecipesRepositoryStub
import com.yourcompany.recipecomposeapp.ui.recipes.RecipeItem
import com.yourcompany.recipecomposeapp.ui.recipes.model.toRecipeItemUiModel
import com.yourcompany.recipecomposeapp.ui.recipes.model.toUiModel
import kotlinx.coroutines.flow.map

@Composable
fun FavoritesScreen(
    repository: RecipesRepositoryStub,
    favoriteManager: FavoriteDataStoreManager,
    onRecipeClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val favoriteRecipeDtosFlow = remember(repository, favoriteManager) {
        favoriteManager.getFavoriteIdsFlow().map { favoriteIds ->
            favoriteIds.mapNotNull { idString ->
                val recipeId = idString.toIntOrNull() ?: return@mapNotNull null

                try {
                    repository.getRecipeById(recipeId)
                } catch (_: Exception) {
                    null
                }
            }
        }
    }

    val favoriteRecipes by favoriteRecipeDtosFlow
        .map { recipeDtos ->
            recipeDtos.map { recipeDto ->
                recipeDto.toUiModel()
            }
        }
        .collectAsState(initial = emptyList())

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ScreenHeader(
            imagePainter = painterResource(id = R.drawable.header_image),
            contentDescription = "Favorites header",
            title = "ИЗБРАННОЕ"
        )

        if (favoriteRecipes.isEmpty()) {
            EmptyFavoritesState(
                modifier = Modifier.weight(1f)
            )
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(
                    start = Dimens.PaddingS,
                    top = Dimens.PaddingM,
                    end = Dimens.PaddingS,
                    bottom = Dimens.PaddingS
                ),
                verticalArrangement = Arrangement.spacedBy(Dimens.PaddingM)
            ) {
                items(
                    items = favoriteRecipes,
                    key = { recipe -> recipe.id }
                ) { recipe ->
                    RecipeItem(
                        recipe = recipe.toRecipeItemUiModel(),
                        onClick = { recipeId ->
                            onRecipeClick(recipeId)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyFavoritesState(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(Dimens.PaddingM),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Добавьте рецепт в избранное, и он появится на этом экране.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    RecipesAppTheme {
        FavoritesScreen(
            repository = RecipesRepositoryStub,
            favoriteManager = FavoriteDataStoreManager(
                context = androidx.compose.ui.platform.LocalContext.current
            ),
            onRecipeClick = { _ -> }
        )
    }
}