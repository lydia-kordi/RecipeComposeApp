package com.yourcompany.recipecomposeapp.ui.recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yourcompany.recipecomposeapp.core.ui.theme.RecipesAppTheme
import com.yourcompany.recipecomposeapp.core.ui.ScreenHeader
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import coil3.compose.rememberAsyncImagePainter
import com.yourcompany.recipecomposeapp.core.ui.theme.Dimens
import com.yourcompany.recipecomposeapp.data.repository.RecipesRepositoryStub
import com.yourcompany.recipecomposeapp.ui.categories.model.toUiModel
import com.yourcompany.recipecomposeapp.ui.recipes.model.RecipeUiModel
import com.yourcompany.recipecomposeapp.ui.recipes.model.toUiModel
@Composable
fun RecipesScreen(
    modifier: Modifier = Modifier,
    categoryId: Int,
    categoryTitle: String
) {
    var recipes by remember {
        mutableStateOf<List<RecipeUiModel>>(emptyList())
    }

    val category = remember(categoryId) {
        RecipesRepositoryStub.getCategories()
            .find { it.id == categoryId }
            ?.toUiModel()
    }

    val headerPainter = rememberAsyncImagePainter(
        model = category?.imageUrl
    )

    LaunchedEffect(categoryId) {
        recipes = RecipesRepositoryStub.getRecipesByCategoryId(categoryId)
            .map { it.toUiModel() }
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ScreenHeader(
            imagePainter = headerPainter,
            contentDescription = categoryTitle,
            title = categoryTitle.uppercase()
        )

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
                items = recipes,
                key = { recipe -> recipe.id }
            ) { recipe ->
                RecipeItem(
                    recipe = recipe,
                    onClick = { _ -> }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipesScreenPreview() {
    RecipesAppTheme {
        RecipesScreen(
            categoryId = 0,
            categoryTitle = "Бургеры"
        )
    }
}