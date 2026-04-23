package com.yourcompany.recipecomposeapp.util

import android.content.Context
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

private const val DATASTORE_NAME = "recipe_app_prefs"
private const val SHARED_PREFS_NAME = "FavoriteRecipes"

val Context.dataStore by preferencesDataStore(
    name = DATASTORE_NAME,
    produceMigrations = { context ->
        listOf(
            SharedPreferencesMigration(
                context = context,
                sharedPreferencesName = SHARED_PREFS_NAME
            )
        )
    }
)

object PreferencesKeys {
    val FAVORITE_RECIPE_IDS = stringSetPreferencesKey("favorite_recipe_ids")
}