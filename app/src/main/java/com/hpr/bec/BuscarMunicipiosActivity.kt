package com.hpr.bec

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_buscar_municipios.*

class BuscarMunicipiosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_municipios)

        initView()
    }

    private fun initView() {

        button_avancar.setOnClickListener {
            startActivity(Intent(this@BuscarMunicipiosActivity,HomeActivity::class.java))
            finish()
        }

    }
}
