package com.hpr.bec

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Gravity
import android.view.View
import br.com.fastmenu.Model.Categoria
import br.com.fastmenu.Model.Pregao
import br.com.fastmenu.Support.Conection.RetrofitConection
import br.com.iterative.abasteceai.Support.Util.AnimationsCustomUtil
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.hpr.bec.adapters.CategoriasAdapter
import com.hpr.bec.adapters.PregaoAdapter
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                //message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_ajuda -> {
               // message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener false
            }
            R.id.navigation_notifications -> {
                startActivity(Intent(this@HomeActivity,NotificacoesActivity::class.java))
                return@OnNavigationItemSelectedListener false
            }
        }
        false
    }

    private var adapter_categorias:CategoriasAdapter? = null
    private var adapter_pregoes:PregaoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        initCategorias()
        initPregoes()
        initViews()

        loadconvites()
    }

    private fun initViews() {

        scroll_main.alpha = 0f

    }

    private fun initPregoes() {

        var lista_preg = ArrayList<Pregao>()
        lista_preg.add(Pregao("",""))
        lista_preg.add(Pregao("",""))
        lista_preg.add(Pregao("",""))
        lista_preg.add(Pregao("",""))
        lista_preg.add(Pregao("",""))
        adapter_pregoes = PregaoAdapter(lista_preg,this)

        adapter_pregoes!!.setListern(object : PregaoAdapter.PregaoClickListern{
            override fun clickPregao() {

                startActivity(Intent(this@HomeActivity,DetalhePregaoActivity::class.java))
            }

        })
        val linearLayout = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        recyclerview_pregoes.layoutManager = linearLayout
        recyclerview_pregoes.adapter = adapter_pregoes
    }

    private fun initCategorias() {

        recyclerview_categorias.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false)

        val snapHelper = GravitySnapHelper(Gravity.START)
        snapHelper.attachToRecyclerView(recyclerview_categorias)

        var lista_cat = ArrayList<Categoria>()
        lista_cat.add(Categoria("Construção civil"))
        lista_cat.add(Categoria("Papelaria"))
        lista_cat.add(Categoria("Informática"))
        lista_cat.add(Categoria("Móveis"))
        lista_cat.add(Categoria("Máquinas"))
        lista_cat.add(Categoria("Serviços"))
        adapter_categorias = CategoriasAdapter(lista_cat,this)

        recyclerview_categorias.adapter = adapter_categorias

    }

    fun loadconvites(){

        progress.visibility = View.VISIBLE

        RetrofitConection()
                .baseAPI
                .getConvites()
                .enqueue(object : Callback<List<Pregao>>{

                    override fun onResponse(call: Call<List<Pregao>>?, response: Response<List<Pregao>>?) {

                        if (response != null && response.isSuccessful){
                            adapter_pregoes?.replaceData(response.body()?.subList(0,20))

                            textview_total.text = response.body()?.size.toString()

                            AnimationsCustomUtil.startAnimationFade(scroll_main,300)

                        }

                        progress.visibility = View.GONE

                    }

                    override fun onFailure(call: Call<List<Pregao>>?, t: Throwable?) {
                        progress.visibility = View.GONE
                    }

                })
    }

}
