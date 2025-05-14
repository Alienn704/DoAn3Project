package com.example.doan3project.stored

import android.content.Context
import android.content.SharedPreferences

class UserStore(context: Context) {
    private val sharedPreferences = "UserPref"
    private var preferences: SharedPreferences? = null

    init {
        preferences = context.getSharedPreferences(sharedPreferences, Context.MODE_PRIVATE)
    }

    fun saveUser(UserList: Int) {
        val editor = preferences!!.edit()
        editor.putInt("user_id", UserList)
        editor.apply()
    }

    fun getId(): Int {
        return preferences!!.getInt("user_id", 0)
    }
}