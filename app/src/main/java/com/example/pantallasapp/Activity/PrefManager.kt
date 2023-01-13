package com.example.pantallasapp.Activity

import android.content.Context
import android.content.SharedPreferences

class PrefManager(context: Context?) {
    val PRIVATE_MODE = 0

    private val PREF_NAME = "datosusuario"

    val pref : SharedPreferences? = context?.getSharedPreferences(PREF_NAME,PRIVATE_MODE)


    fun removeData(){
        val editor : SharedPreferences.Editor? = pref?.edit()
        editor?.clear()
        editor?.commit()
    }
}