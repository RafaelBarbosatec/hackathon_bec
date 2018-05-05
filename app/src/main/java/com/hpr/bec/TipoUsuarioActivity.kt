package com.hpr.bec

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.activity_tipo_usuario.*

class TipoUsuarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tipo_usuario)

        initViews()
    }

    private fun initViews() {

        ll_card_empresa.setOnClickListener {
            changeSelected(1)
        }

        ll_card_visitante.setOnClickListener {
            changeSelected(2)
        }

        button_avancar.setOnClickListener {
            startActivity(Intent(this@TipoUsuarioActivity,HomeActivity::class.java))
        }
    }

    fun changeSelected(position: Int){
        when(position){
            1->{
                ll_card_empresa.setBackgroundColor(ContextCompat.getColor(this,R.color.colorCardSelected))
                ll_card_visitante.setBackgroundColor(ContextCompat.getColor(this,R.color.colorCardnotSelected))
                textview_desc.text = "Você é uma empresa e tem interesse consultar pregões que possam participar"
            }
            2->{
                ll_card_empresa.setBackgroundColor(ContextCompat.getColor(this,R.color.colorCardnotSelected))
                ll_card_visitante.setBackgroundColor(ContextCompat.getColor(this,R.color.colorCardSelected))
                textview_desc.text = ""

            }
        }
    }
}
