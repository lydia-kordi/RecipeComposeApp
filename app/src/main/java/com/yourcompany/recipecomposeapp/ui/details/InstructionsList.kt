package com.yourcompany.recipecomposeapp.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yourcompany.recipecomposeapp.core.ui.theme.Dimens
import com.yourcompany.recipecomposeapp.core.ui.theme.RecipesAppTheme

@Composable
fun InstructionsList(
    instructions: List<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Способ приготовления",
            modifier = Modifier.padding(bottom = Dimens.PaddingS)
        )

        instructions.forEachIndexed { index, instruction ->
            InstructionItem(
                stepNumber = index + 1,
                instruction = instruction.removePrefix("${index + 1}. ").trim(),
                modifier = Modifier.padding(bottom = Dimens.PaddingS)
            )
        }
    }
}

@Composable
fun InstructionItem(
    stepNumber: Int,
    instruction: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = "$stepNumber.")
        Text(
            text = instruction,
            modifier = Modifier.padding(start = Dimens.PaddingS)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InstructionsListPreview() {
    RecipesAppTheme {
        InstructionsList(
            instructions = listOf(
                "1. В глубокой миске смешайте говяжий фарш, лук, чеснок, соль и перец.",
                "2. Разогрейте сковороду на среднем огне. Обжаривайте котлеты с каждой стороны в течение 4-5 минут.",
                "3. Подготовьте булочки и обжарьте их до золотистой корочки."
            )
        )
    }
}