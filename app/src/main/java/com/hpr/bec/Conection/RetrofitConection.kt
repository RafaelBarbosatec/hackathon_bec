package br.com.fastmenu.Support.Conection

import com.google.gson.GsonBuilder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by rafaelbarbosa on 26/12/17.
 */

class RetrofitConection() {

    var baseAPI: BaseAPI

    init {

        val gson = GsonBuilder()
                .setLenient()
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.bec.sp.gov.br/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        baseAPI = retrofit.create(BaseAPI::class.java)

    }

}
