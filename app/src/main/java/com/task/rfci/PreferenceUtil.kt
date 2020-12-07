package com.task.rfci

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context : Context) {
    private var sharedprefrences : SharedPreferences
    private var PREFERENCES_NAME = "PREFERENCE_NAME"
    var editor : SharedPreferences.Editor

    init {
        sharedprefrences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        editor = sharedprefrences.edit()
    }

    fun put(key: String, value: String){
        editor.putString(key, value)
                .apply()
    }

    fun getString( key: String ): String? {
        return sharedprefrences.getString(key, null)
    }
}