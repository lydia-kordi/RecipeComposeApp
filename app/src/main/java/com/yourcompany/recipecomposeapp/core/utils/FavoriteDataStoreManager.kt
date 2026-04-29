package com.yourcompany.recipecomposeapp.core.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.yourcompany.recipecomposeapp.util.PreferencesKeys
import com.yourcompany.recipecomposeapp.util.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteDataStoreManager(
    private val context: Context
) {
    fun getFavoriteIdsFlow(): Flow<Set<String>> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.FAVORITE_RECIPE_IDS] ?: emptySet()
        }
    }

    fun isFavoriteFlow(recipeId: Int): Flow<Boolean> {
        return getFavoriteIdsFlow().map { favoriteIds ->
            recipeId.toString() in favoriteIds
        }
    }

    fun getFavoriteCountFlow(): Flow<Int> {
        return getFavoriteIdsFlow().map { favoriteIds ->
            favoriteIds.size
        }
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