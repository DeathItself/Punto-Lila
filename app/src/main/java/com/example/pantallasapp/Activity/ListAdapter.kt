package com.example.pantallasapp.Activity

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pantallasapp.databinding.ListEventosBinding
import coil.load
import com.example.pantallasapp.Fragments.ListaMenu

class ListAdapter: RecyclerView.Adapter<ListAdapter.ListViewHolder>(){
    var lista:MutableList<ListaMenu> = ArrayList()
    lateinit var context: Context

    fun ListaRecyclerAdapter(list:MutableList<ListaMenu>, contxt: Context){
        this.lista = list
        this.context = contxt
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return ListViewHolder(ListEventosBinding.inflate(
            layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        with(holder){
            with(lista[position]){
                binding.boton1.text = this.eventName
                binding.image1.load(this.eventPhoto)

            }
        }
        val item = lista[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, lista[position].eventName, Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return lista.size

    }

    class ListViewHolder(val binding: ListEventosBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(lista: ListaMenu) {}
    }

}
