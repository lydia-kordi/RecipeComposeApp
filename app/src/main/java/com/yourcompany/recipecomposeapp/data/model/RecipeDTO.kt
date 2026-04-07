package com.yourcompany.recipecomposeapp.data.model

data class RecipeDTO(
    val id: Int,
    val title: String,
    val ingredients: List<IngredientDTO>,
    val method: List<String>,
    val imageUrl: String
)
