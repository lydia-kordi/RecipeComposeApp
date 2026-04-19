package com.yourcompany.recipecomposeapp.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.yourcompany.recipecomposeapp.core.ui.theme.Dimens

@Composable
fun ScreenHeader(
    imagePainter: Painter,
    contentDescription: String,
    title: String,
) {
    Box(
        modifier = Modifier
            .height(Dimens.HeaderHeight)
            .fillMaxWidth()
    ) {
        Image(
            painter = imagePainter,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Surface(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    start = Dimens.PaddingM,
                    end = Dimens.PaddingM,
                    bottom = Dimens.PaddingM
                ),
            shape = RoundedCornerShape(Dimens.CornerRadiusS),
            border = BorderStroke(Dimens.BorderWidthS, MaterialTheme.colorScheme.outline),
            color = MaterialTheme.colorScheme.surface
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(
                    horizontal = Dimens.PaddingM,
                    vertical = Dimens.PaddingS
                ),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.primary

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenHeaderPreview() {
    ScreenHeader(
        imagePainter = ColorPainter(Color.LightGray),
        contentDescription = "Preview header image",
        title = "Категории"
    )
}