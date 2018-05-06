package com.hpr.bec

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tipo_usuario.*

class TipoUsuarioActivity : AppCompatActivity() {

    private var selectedType = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tipo_usuario)

        initViews()
    }

    private fun initViews() {

        ll_card_empresa.setOnClickListener {
            changeSelected(1)
            selectedType = 1
        }

        ll_card_visitante.setOnClickListener {
            changeSelected(2)
            selectedType = 2
        }

        button_avancar.setOnClickListener {

            if (selectedType == 0){

                Toast.makeText(this@TipoUsuarioActivity,"Selecione qual seu perfil de usuário",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            SheredUtil.saveTipoUsuario(this@TipoUsuarioActivity,if (selectedType == 1){SheredUtil.TIPO_EMPRESA} else SheredUtil.TIPO_VISITANTE)

            if (selectedType == 1) {
                startActivity(Intent(this@TipoUsuarioActivity, HomeActivity::class.java))
            }else{
                startActivity(Intent(this@TipoUsuarioActivity, BuscarMunicipiosActivity::class.java))
            }
            finish()

        }
    }

    fun changeSelected(position: Int){
        when(position){
            1->{
                ll_card_empresa.setBackgroundColor(ContextCompat.getColor(this,R.color.colorCardSelected))
                ll_card_visitante.setBackgroundColor(ContextCompat.getColor(this,R.color.colorCardnotSelected))
                textview_desc.text = "Se você é uma empresa, independete do tamanho, e deseja ofertar serviços ou produtos ao estado"
            }
            2->{
                ll_card_empresa.setBackgroundColor(ContextCompat.getColor(this,R.color.colorCardnotSelected))
                ll_card_visitante.setBackgroundColor(ContextCompat.getColor(this,R.color.colorCardSelected))
                textview_desc.text = "Deja saber se seu cidade participa de processo de compras públicas de forma transparente"

            }
        }
    }
}
