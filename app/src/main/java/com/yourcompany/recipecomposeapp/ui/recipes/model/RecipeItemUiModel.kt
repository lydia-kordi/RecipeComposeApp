package com.yourcompany.recipecomposeapp.ui.recipes.model

import androidx.compose.runtime.Immutable
import com.yourcompany.recipecomposeapp.data.model.RecipeDto
import com.yourcompany.recipecomposeapp.ui.ASSETS_URI_PREFIX

@Immutable
data class RecipeItemUiModel(
    val id: Int,
    val title: String,
    val imageUrl: String
)

fun RecipeDto.toRecipeItemUiModel() = RecipeItemUiModel(
    id = id,
    title = title,
    imageUrl = if (imageUrl.startsWith("http")) imageUrl else ASSETS_URI_PREFIX + imageUrl
)

fun RecipeUiModel.toRecipeItemUiModel() = RecipeItemUiModel(
    id = id,
    title = title,
    imageUrl = imageUrl
)