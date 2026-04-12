package com.yourcompany.recipecomposeapp.ui.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.yourcompany.recipecomposeapp.R
import com.yourcompany.recipecomposeapp.core.ui.theme.RecipesAppTheme
import com.yourcompany.recipecomposeapp.core.ui.ScreenHeader
import com.yourcompany.recipecomposeapp.core.ui.theme.Dimens
import com.yourcompany.recipecomposeapp.data.repository.RecipesRepositoryStub
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.remember
import com.yourcompany.recipecomposeapp.ui.categories.model.toUiModel


@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier,
    onCategoryClick: (Int, String) -> Unit
) {
    val categories = remember { RecipesRepositoryStub.getCategories().map { it.toUiModel() } }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ScreenHeader(
            imagePainter = painterResource(id = R.drawable.header_image),
            contentDescription = "Categories header",
            title = "КАТЕГОРИИ"
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(Dimens.PaddingM),
            verticalArrangement = Arrangement.spacedBy(Dimens.PaddingM),
            horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingM)
        ) {
            items(categories) { category ->
                CategoryItem(
                    category = category,
                    onClick = {
                        onCategoryClick(category.id, category.title)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesScreenPreview() {
    RecipesAppTheme {
        CategoriesScreen(
            onCategoryClick = { _, _ -> }
        )
    }
}