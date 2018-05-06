package com.hpr.bec

import android.app.Activity
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * Created by rafaelbarbosa on 22/01/18.
 */
object SheredUtil{

    private val PREF_NAME = "com.hpr.bec"
    private val PARAM_TIPO_USUARIO = "list_brindes"

    val TIPO_EMPRESA = "EMPRESA"
    val TIPO_VISITANTE = "VISITANTE"

    fun saveTipoUsuario(context: Context, tipo: String){

        var sp = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE)
        var editor = sp.edit()
        editor.putString(PARAM_TIPO_USUARIO,tipo)
        editor.commit()

    }

    fun getTipoUsuari(context: Context): String{
        var sp = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE)
        return sp.getString(PARAM_TIPO_USUARIO,"")
    }

}