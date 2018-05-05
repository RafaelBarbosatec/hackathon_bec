package com.hpr.bec.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fastmenu.Model.Categoria
import com.hpr.bec.R
import kotlinx.android.synthetic.main.item_categoria.view.*
/**
 * Created by dev on 25/10/17.
 */
class CategoriasAdapter(var mlista: List<Categoria>,
                        val context: Context) : RecyclerView.Adapter<CategoriasAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        var view = LayoutInflater.from(parent?.context).inflate(R.layout.item_categoria, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val categoria = mlista[position]

        holder?.itemView?.tv_nome?.text = categoria.nome

    }

    fun replaceData(books:List<Categoria>){

        mlista = books
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return mlista.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

}