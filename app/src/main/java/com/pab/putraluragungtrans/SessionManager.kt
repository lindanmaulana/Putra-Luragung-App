package com.pab.putraluragungtrans

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val PREF_NAME = "PutraLuragungPrefs"
    private val IS_LOGIN = "is_login"
    private val KEY_EMAIL = "user_email"

    private val pref: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = pref.edit()

    fun createLoginSession(email: String) {
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_EMAIL, email)
        editor.commit()
    }

    fun getEmail(): String? {
        return pref.getString(KEY_EMAIL, null)
    }

    fun isLoggedIn(): Boolean {
        return pref.getBoolean(IS_LOGIN, false)
    }

    fun logout() {
        editor.clear()
        editor.commit()
    }
}