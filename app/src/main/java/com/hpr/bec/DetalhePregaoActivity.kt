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
