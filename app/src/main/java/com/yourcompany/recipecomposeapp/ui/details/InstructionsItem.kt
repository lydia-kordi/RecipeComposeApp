package com.yourcompany.recipecomposeapp.ui.details

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yourcompany.recipecomposeapp.core.ui.theme.Dimens

@Composable
fun InstructionItem(
    stepNumber: Int,
    instruction: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$stepNumber. $instruction",
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier.padding(
            horizontal = Dimens.PaddingM,
            vertical = Dimens.PaddingS
        )
    )
}