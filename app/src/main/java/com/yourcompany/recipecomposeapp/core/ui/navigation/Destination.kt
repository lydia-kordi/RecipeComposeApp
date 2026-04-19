package com.yourcompany.recipecomposeapp.core.ui.navigation

sealed class Destination(val route: String) {
    data object Categories : Destination("categories")
    data object Favorites : Destination("favorites")
    data object Recipes : Destination("recipes/{categoryId}") {
        fun createRoute(categoryId: Int): String = "recipes/$categoryId"
    }
    data object RecipeDetails: Destination(route = "recipe/{recipeId}") {
        fun createRoute(recipeId: Int): String = "recipe/$recipeId"
    }
}