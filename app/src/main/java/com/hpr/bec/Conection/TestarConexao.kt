package br.com.fastmenu.Support.Conection

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by rafaelbarbosa on 27/12/17.
 */

object TestarConexao{

    /**
     * Função que testa a existencia de conectividade (Movel/WIFI)
     */
    fun verificaConexao(contexto: Context): Boolean {

        return try {

            //Pego a conectividade do contexto o qual o metodo foi chamado
            val cm = contexto.getSystemService(Activity.CONNECTIVITY_SERVICE) as ConnectivityManager

            //Crio o objeto netInfo que recebe as informacoes da Network
            val netInfo = cm.activeNetworkInfo

            netInfo != null && netInfo.isConnectedOrConnecting && netInfo.isAvailable

        }catch (e: Exception){

            true

        }

    }

}