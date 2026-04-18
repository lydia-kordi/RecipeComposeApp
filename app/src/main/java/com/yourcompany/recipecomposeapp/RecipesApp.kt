package com.yourcompany.recipecomposeapp

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
import com.yourcompany.recipecomposeapp.ui.favorites.FavoritesScreen
import com.yourcompany.recipecomposeapp.ui.recipes.RecipesScreen

@Composable
fun RecipesApp() {
    val navController = rememberNavController()

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
                        onRecipeClick = { _ -> }
                    )
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