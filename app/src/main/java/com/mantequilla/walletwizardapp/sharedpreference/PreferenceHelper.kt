package com.mantequilla.walletwizardapp.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceHelper @Inject constructor(context: Context) {
    private val PREF_NAME = "DB_LOCAL"
    private val sharedPref : SharedPreferences
    val editor : SharedPreferences.Editor
    init {
        sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    fun put(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun getString(key: String) : String? {
        return sharedPref.getString(key, null)
    }

    fun put(key: String, value : Boolean) {
        editor.putBoolean(key, value).apply()
    }

    fun getBoolean(key : String) : Boolean {
        return sharedPref.getBoolean(key, false)
    }

    fun put(key : String, value : Int) {
        editor.putInt(key, value).apply()
    }

    fun getInt(key : String) : Int {
        return sharedPref.getInt(key, 0)
    }

    fun clear() {
        editor.clear().apply()
    }
}