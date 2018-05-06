package br.com.fastmenu.Support.Conection

import br.com.fastmenu.Model.Pregao
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.FieldMap
import retrofit2.http.Url
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET



/**
 * Created by rafaelbarbosa on 26/12/17.
 */

interface BaseAPI {

    /*@GET("aula/books.json")
    fun getBooks(): Call<List<Book>>*/

    @GET("BEC_API/API/Convite/NegociacaoItemOC")
    fun getConvites(): Call<List<Pregao>>


}
