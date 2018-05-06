package com.hpr.bec

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
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
    private var tipoUsuario: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        tipoUsuario = SheredUtil.getTipoUsuari(this)
        initCategorias()
        initPregoes()
        initViews()

        loadconvites()

        startTime()
    }

    private fun initViews() {

        scroll_main.alpha = 0f

        if (tipoUsuario == SheredUtil.TIPO_VISITANTE){
            textview_desc_cat.text = "Recentes"
        }


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
                            adapter_pregoes?.replaceData(response.body()?.subList(0,25))

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


    fun showNotification() {
        var intent = Intent(this@HomeActivity, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK;
        var pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)


        var mBuilder = NotificationCompat.Builder(this, "1")
                .setSmallIcon(R.drawable.notification_icon_background)
                .setContentTitle("Licitações disponíveis")
                .setContentText("Você tem 4 licitações disponíveis para lançar um pregão.Clique aqui para ser redirecinado.")
                .setStyle(NotificationCompat.BigTextStyle()
                        .bigText("Você tem 4 licitações disponíveis para lançar um pregão.Clique aqui para ser redirecinado."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)

        val nmc = NotificationManagerCompat.from(this)
        nmc.notify(1, mBuilder.build())

    }

    fun startTime() {

        var time = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                showNotification()
            }

        }.start()
    }

}
