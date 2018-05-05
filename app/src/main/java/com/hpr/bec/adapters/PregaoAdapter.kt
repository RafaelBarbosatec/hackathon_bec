package com.hpr.bec.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fastmenu.Model.Categoria
import br.com.fastmenu.Model.Pregao
import com.hpr.bec.R
import kotlinx.android.synthetic.main.item_categoria.view.*
import kotlinx.android.synthetic.main.item_pregao.view.*

/**
 * Created by dev on 25/10/17.
 */
class PregaoAdapter(var mlista: List<Pregao>,
                    val context: Context) : RecyclerView.Adapter<PregaoAdapter.MyViewHolder>(){


    private var listern:PregaoClickListern? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        var view = LayoutInflater.from(parent?.context).inflate(R.layout.item_pregao, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

//        val categoria = mlista[position]
//
//        holder?.itemView?.tv_nome?.text = categoria.nome

        holder.itemView?.ll_item?.setOnClickListener {
            listern?.clickPregao()
        }
    }

    fun setListern(l:PregaoClickListern){
        listern = l
    }

    fun replaceData(books:List<Pregao>){

        mlista = books
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return mlista.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface PregaoClickListern{
        fun clickPregao()
    }
}