package com.hpr.bec

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.hpr.bec.adapters.StepAdapter
import com.hpr.bec.model.StepPregao
import kotlinx.android.synthetic.main.activity_pregao.*
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.MotionEvent
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper


class PregaoActivity : AppCompatActivity() {

    private var adapter: StepAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregao)

        initSteps()
        initViews()
    }

    private fun initViews() {

        setSupportActionBar(toolbar_pregao)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""


    }

    private fun initSteps() {


        recyclerview_step.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false)

        val snapHelper = GravitySnapHelper(Gravity.START)
        snapHelper.attachToRecyclerView(recyclerview_step)

        var lista_step = ArrayList<StepPregao>()
        lista_step.add(StepPregao("Proposta",true))
        lista_step.add(StepPregao("Abertura C. Pública"))
        lista_step.add(StepPregao("Classificação"))
        lista_step.add(StepPregao("Lances"))
        lista_step.add(StepPregao("Negocioação"))
        lista_step.add(StepPregao("Habilitação"))
        lista_step.add(StepPregao("Recurso"))
        lista_step.add(StepPregao("Encerrado"))

        adapter = StepAdapter(lista_step,this)

        recyclerview_step.adapter = adapter

        adapter?.listern = object : StepAdapter.StepSelectedListern{
            override fun stepSelected(position: Int) {
                (recyclerview_step.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(position, 4)
            }

        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
