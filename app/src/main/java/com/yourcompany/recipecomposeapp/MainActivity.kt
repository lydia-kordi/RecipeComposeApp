package com.yourcompany.recipecomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.yourcompany.recipecomposeapp.ui.theme.RecipesComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipesComposeAppTheme {
                Scaffold { paddingValues ->
                    Text("Recipes App", modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}