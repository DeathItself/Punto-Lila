package com.example.pantallasapp.ui.ReciclerView

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pantallasapp.ListaMenu
import com.example.pantallasapp.R

class ListViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val lista = view.findViewById<TextView>(R.id.ListaId)
    fun render(listaMenu: ListaMenu){
        lista.text = listaMenu.item
    }
}