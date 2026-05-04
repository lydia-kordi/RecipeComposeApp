package com.yourcompany.recipecomposeapp.core.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.yourcompany.recipecomposeapp.core.utils.FavoriteDataStoreManager
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.PaddingValues

@Composable
fun BottomNavigation(
    onCategoriesClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val favoriteDataStoreManager = remember(context) {
        FavoriteDataStoreManager(context)
    }

    val favoriteCount by favoriteDataStoreManager
        .getFavoriteCountFlow()
        .collectAsState(initial = 0)

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
                contentColor = MaterialTheme.colorScheme.onError,
            ),
            contentPadding = PaddingValues(Dimens.NoPadding)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "ИЗБРАННОЕ",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(end = if (favoriteCount > 0) Dimens.PaddingXL else Dimens.NoPadding)
                )
                if (favoriteCount > 0) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = Dimens.PaddingS)
                            .size(Dimens.BadgeSizeS)
                            .background(
                                color = MaterialTheme.colorScheme.onError,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = favoriteCount.toString(),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                }
            }

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