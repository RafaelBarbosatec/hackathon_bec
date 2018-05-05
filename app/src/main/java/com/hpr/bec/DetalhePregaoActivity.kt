package com.hpr.bec

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_detalhe_pregao.*

class DetalhePregaoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_pregao)

        initViews()
    }

    private fun initViews() {

        setSupportActionBar(toolbar_detalhe)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId ==  android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


}
