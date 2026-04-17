package com.yourcompany.recipecomposeapp.ui.recipes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.compose.runtime.Immutable
import com.yourcompany.recipecomposeapp.data.model.IngredientDto

@Immutable
@Parcelize
data class IngredientUiModel(
    val name: String,
    val quantity: String,
    val unitOfMeasure: String
) : Parcelable

fun IngredientDto.toUiModel() = IngredientUiModel(
    name = description,
    quantity = quantity,
    unitOfMeasure = unitOfMeasure
)