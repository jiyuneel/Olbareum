package com.olbareum.olbareum

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {

    private val preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun getAccessToken(): String? {
        return preferences.getString(ACCESS_TOKEN_KEY, null)
    }

    fun setAccessToken(accessToken: String) {
        preferences.edit().putString(ACCESS_TOKEN_KEY, accessToken).apply()
    }

    companion object {
        private const val PREFS_NAME = "com.olbareum.olbareum.prefs"
        private const val ACCESS_TOKEN_KEY = "accessToken"
    }
}