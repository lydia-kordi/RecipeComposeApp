package com.yourcompany.recipecomposeapp.core.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.yourcompany.recipecomposeapp.util.PreferencesKeys
import com.yourcompany.recipecomposeapp.util.dataStore
import kotlinx.coroutines.flow.first

class FavoriteDataStoreManager(
    private val context: Context
) {

    suspend fun isFavorite(recipeId: Int): Boolean {
        val favorites = context.dataStore.data.first()[PreferencesKeys.FAVORITE_RECIPE_IDS] ?: emptySet()
        return recipeId.toString() in favorites
    }

    suspend fun addFavorite(recipeId: Int) {
        context.dataStore.edit { preferences ->
            val currentFavorites =
                preferences[PreferencesKeys.FAVORITE_RECIPE_IDS] ?: emptySet()

            preferences[PreferencesKeys.FAVORITE_RECIPE_IDS] =
                currentFavorites + recipeId.toString()
        }
    }

    suspend fun removeFavorite(recipeId: Int) {
        context.dataStore.edit { preferences ->
            val currentFavorites =
                preferences[PreferencesKeys.FAVORITE_RECIPE_IDS] ?: emptySet()

            preferences[PreferencesKeys.FAVORITE_RECIPE_IDS] =
                currentFavorites - recipeId.toString()
        }
    }
}