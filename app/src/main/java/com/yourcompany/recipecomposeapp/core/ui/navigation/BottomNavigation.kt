package com.yourcompany.recipecomposeapp.core.ui.navigation

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yourcompany.recipecomposeapp.core.ui.theme.Dimens
import com.yourcompany.recipecomposeapp.core.ui.theme.RecipesAppTheme
import androidx.compose.foundation.layout.navigationBarsPadding

@Composable
fun BottomNavigation(
    onCategoriesClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(
                horizontal = Dimens.PaddingM,
                vertical = Dimens.PaddingXS,
            ),
        horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingS)
    ) {
        Button(
            onClick = onCategoriesClick,
            modifier = Modifier
                .weight(1f)
                .height(Dimens.ButtonHeight),
            shape = RoundedCornerShape(Dimens.ButtonCornerRadius),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            )
        ) {
            Text(
                text = "КАТЕГОРИИ",
                style = MaterialTheme.typography.labelLarge
            )

        }

        Button(
            onClick = onFavoriteClick,
            modifier = Modifier
                .weight(1f)
                .height(Dimens.ButtonHeight),
            shape = RoundedCornerShape(Dimens.ButtonCornerRadius),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError
            )
        ) {
            Text(
                text = "ИЗБРАННОЕ",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    RecipesAppTheme {
        BottomNavigation(
            onCategoriesClick = {},
            onFavoriteClick = {}
        )
    }
}