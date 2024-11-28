package com.olbareum.olbareum

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class MyApplication : Application() {
    companion object {
        lateinit var preferences: PreferenceUtil
    }

    override fun onCreate() {
        preferences = PreferenceUtil(applicationContext)
        super.onCreate()

        // 다크모드 비활성화
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}