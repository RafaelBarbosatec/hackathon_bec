package com.hpr.bec

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_detalhe_pregao.*

class DetalhePregaoActivity : AppCompatActivity() {

    private var tipoUsuario: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_pregao)

        tipoUsuario = SheredUtil.getTipoUsuari(this)

        initViews()
    }

    private fun initViews() {

        setSupportActionBar(toolbar_detalhe)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        button_participar.setOnClickListener {

            if (tipoUsuario ==  SheredUtil.TIPO_VISITANTE){

                val shareBody = "Here is the share content body"
                val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here")
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
                startActivity(Intent.createChooser(sharingIntent, resources.getString(R.string.app_name)))

            }else
            startActivity(Intent(this@DetalhePregaoActivity,PregaoActivity::class.java))
        }

        if (tipoUsuario ==  SheredUtil.TIPO_VISITANTE){
            button_participar.text = "INDIQUE A UMA EMPRESA"
        }

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId ==  android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


}
