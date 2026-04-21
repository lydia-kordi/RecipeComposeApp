package com.yourcompany.recipecomposeapp.core.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

private const val PREFS_NAME = "favorite_prefs"
private const val FAVORITE_RECIPE_IDS_KEY = "favorite_recipe_ids"

class FavoritePrefsManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun isFavorite(recipeId: Int): Boolean {
        return prefs.getStringSet(FAVORITE_RECIPE_IDS_KEY, emptySet())
            ?.contains(recipeId.toString()) == true
    }

    fun addToFavorites(recipeId: Int) {
        val currentFavorites = prefs.getStringSet(FAVORITE_RECIPE_IDS_KEY, emptySet())
            ?.toMutableSet()
            ?: mutableSetOf()

        currentFavorites.add(recipeId.toString())

        prefs.edit {
            putStringSet(FAVORITE_RECIPE_IDS_KEY, currentFavorites)
        }
    }

    fun removeFromFavorites(recipeId: Int) {
        val currentFavorites = prefs.getStringSet(FAVORITE_RECIPE_IDS_KEY, emptySet())
            ?.toMutableSet()
            ?: mutableSetOf()

        currentFavorites.remove(recipeId.toString())

        prefs.edit {
            putStringSet(FAVORITE_RECIPE_IDS_KEY, currentFavorites)
        }
    }

    fun getAllFavorites(): Set<String> {
        return prefs.getStringSet(FAVORITE_RECIPE_IDS_KEY, emptySet()) ?: emptySet()
    }
}