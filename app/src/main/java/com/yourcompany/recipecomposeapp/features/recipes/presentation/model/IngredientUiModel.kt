package com.yourcompany.recipecomposeapp.features.recipes.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.compose.runtime.Immutable
import com.yourcompany.recipecomposeapp.data.model.IngredientDto

@Immutable
@Parcelize
data class IngredientUiModel(
    val name: String,
    val quantity: Double?,
    val unitOfMeasure: String
) : Parcelable

fun IngredientDto.toUiModel() = IngredientUiModel(
    name = description,
    quantity = quantity,
    unitOfMeasure = unitOfMeasure
)