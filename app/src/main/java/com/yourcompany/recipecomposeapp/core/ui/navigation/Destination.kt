package com.yourcompany.recipecomposeapp.core.ui.navigation

import com.yourcompany.recipecomposeapp.ui.PARAM_RECIPE_ID
import com.yourcompany.recipecomposeapp.ui.DEEP_LINK_BASE_URL

sealed class Destination(val route: String) {
    data object Categories : Destination("categories")
    data object Favorites : Destination("favorites")
    data object Recipes : Destination("recipes/{categoryId}") {
        fun createRoute(categoryId: Int): String = "recipes/$categoryId"
    }

    data object RecipeDetails : Destination(route = "recipe/{$PARAM_RECIPE_ID}") {
        fun createRoute(recipeId: Int): String = "recipe/$recipeId"
    }
}

fun createRecipeDeepLink(recipeId: Int): String =
    "$DEEP_LINK_BASE_URL/recipe/$recipeId"