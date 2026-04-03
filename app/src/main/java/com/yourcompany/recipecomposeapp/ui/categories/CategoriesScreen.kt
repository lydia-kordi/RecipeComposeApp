package com.yourcompany.recipecomposeapp.ui.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yourcompany.recipecomposeapp.R
import com.yourcompany.recipecomposeapp.core.ui.theme.RecipesAppTheme
import com.yourcompany.recipecomposeapp.ui.categories.components.ScreenHeader

@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ScreenHeader(
            imagePainter = painterResource(id = R.drawable.categories_header),
            contentDescription = "Categories header",
            title = "Категории"
        )

        Text(
            text = "Список категорий будет здесь",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesScreenPreview() {
    RecipesAppTheme {
        CategoriesScreen()
    }
}