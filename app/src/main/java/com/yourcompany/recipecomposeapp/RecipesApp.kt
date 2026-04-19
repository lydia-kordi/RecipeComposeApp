package com.yourcompany.recipecomposeapp

import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yourcompany.recipecomposeapp.core.ui.navigation.BottomNavigation
import com.yourcompany.recipecomposeapp.core.ui.navigation.Destination
import com.yourcompany.recipecomposeapp.core.ui.theme.RecipesAppTheme
import com.yourcompany.recipecomposeapp.ui.categories.CategoriesScreen
import com.yourcompany.recipecomposeapp.ui.details.RecipeDetailsScreen
import com.yourcompany.recipecomposeapp.ui.favorites.FavoritesScreen
import com.yourcompany.recipecomposeapp.ui.recipes.RecipesScreen
import androidx.compose.runtime.LaunchedEffect
import com.yourcompany.recipecomposeapp.data.repository.RecipesRepositoryStub
import com.yourcompany.recipecomposeapp.ui.DEEP_LINK_SCHEME
import com.yourcompany.recipecomposeapp.ui.PARAM_RECIPE_ID
import com.yourcompany.recipecomposeapp.ui.recipes.model.toUiModel
import kotlinx.coroutines.delay

@Composable
fun RecipesApp(deepLinkIntent: Intent? = null) {
    val navController = rememberNavController()
    LaunchedEffect(deepLinkIntent) {
        deepLinkIntent?.data?.let { uri ->
            val recipeId: Int? = when (uri.scheme) {
                DEEP_LINK_SCHEME -> {
                    if (uri.host == "recipe") {
                        uri.pathSegments.firstOrNull()?.toIntOrNull()
                    } else {
                        null
                    }
                }
                "https", "http" -> {
                    if (uri.pathSegments.firstOrNull() == "recipe") {
                        uri.pathSegments.getOrNull(1)?.toIntOrNull()
                    } else {
                        null
                    }
                }
                else -> null
            }

            if (recipeId != null) {
                delay(100)
                navController.navigate(Destination.RecipeDetails.createRoute(recipeId))
            }
        }
    }

    RecipesAppTheme {
        Scaffold(
            bottomBar = {
                BottomNavigation(
                    onCategoriesClick = {
                        navController.navigate(Destination.Categories.route) {
                            launchSingleTop = true
                        }
                    },
                    onFavoriteClick = {
                        navController.navigate(Destination.Favorites.route) {
                            launchSingleTop = true
                        }
                    }
                )
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Destination.Categories.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(Destination.Categories.route) {
                    CategoriesScreen(
                        modifier = Modifier,
                        onCategoryClick = { categoryId, _ ->
                            navController.navigate(
                                Destination.Recipes.createRoute(categoryId)
                            )
                        }
                    )
                }

                composable(Destination.Favorites.route) {
                    FavoritesScreen(
                        modifier = Modifier
                    )
                }

                composable(
                    route = Destination.Recipes.route,
                    arguments = listOf(
                        navArgument("categoryId") {
                            type = NavType.IntType
                        }
                    )
                ) { backStackEntry ->
                    val categoryId = backStackEntry.arguments?.getInt("categoryId")
                        ?: error("Category ID is required")

                    RecipesScreen(
                        modifier = Modifier,
                        categoryId = categoryId,
                        onRecipeClick = { recipeId ->
                            navController.navigate(
                                Destination.RecipeDetails.createRoute(recipeId)
                            )
                        }
                    )
                }
                composable(
                    route = Destination.RecipeDetails.route,
                    arguments = listOf(
                        navArgument(PARAM_RECIPE_ID) {
                            type = NavType.IntType
                        }
                    )
                ) { backStackEntry ->
                    val recipeId = backStackEntry.arguments?.getInt(PARAM_RECIPE_ID)
                        ?: error("Recipe ID is required")

                    val recipe = RecipesRepositoryStub
                        .getRecipeById(recipeId)
                        ?.toUiModel()

                    if (recipe != null) {
                        RecipeDetailsScreen(recipe = recipe)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipesAppPreview() {
    RecipesApp()
}