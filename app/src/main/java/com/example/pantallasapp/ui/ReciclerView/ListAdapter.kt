package com.example.pantallasapp.ui.ReciclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pantallasapp.ListaMenu
import com.example.pantallasapp.R

class ListAdapter(private val ValorLista:List<ListaMenu>) : RecyclerView.Adapter<ListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
            return ListViewHolder(layoutInflater.inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = ValorLista[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = ValorLista.size



}