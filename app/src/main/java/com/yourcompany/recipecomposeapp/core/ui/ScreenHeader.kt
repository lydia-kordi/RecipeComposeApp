package com.yourcompany.recipecomposeapp.core.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.IconButton
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
import androidx.compose.material3.Icon
import com.yourcompany.recipecomposeapp.R
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource

@Composable
fun ScreenHeader(
    imagePainter: Painter,
    contentDescription: String,
    title: String,
    showShareButton: Boolean = false,
    onShareClick: () -> Unit = {},
    showFavoriteButton: Boolean = false,
    isFavorite: Boolean = false,
    onFavoriteToggle: () -> Unit = {}
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

        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(Dimens.PaddingM)
        ) {
            if (showFavoriteButton) {
                IconButton(onClick = onFavoriteToggle) {
                    Crossfade(
                        targetState = isFavorite,
                        animationSpec = tween(durationMillis = 300),
                        label = "favorite_animation"
                    ) { isCurrentlyFavorite ->
                        val heartIcon = rememberVectorPainter(
                            image = ImageVector.vectorResource(
                                id = if (isCurrentlyFavorite) R.drawable.ic_heart else R.drawable.ic_heart_empty
                            )
                        )

                        Icon(
                            painter = heartIcon,
                            contentDescription = "Избранное",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(Dimens.IconSizeM)
                        )
                    }
                }
            }

            if (showShareButton) {
                Surface(
                    modifier = Modifier.padding(top = Dimens.PaddingS),
                    shape = RoundedCornerShape(Dimens.CornerRadiusS),
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
                ) {

                    IconButton(onClick = onShareClick) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Поделиться",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }

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