package com.example.pantallasapp.Adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pantallasapp.Fragments.Fragment_eventos
import com.example.pantallasapp.Fragments.Fragment_eventosDirections
import com.example.pantallasapp.databinding.ListEventosBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.ListViewHolder>(){
    var lista:MutableList<Fragment_eventos.ListaMenu> = ArrayList()
    lateinit var context: Context
    private lateinit var binding: ListEventosBinding

    fun ListaRecyclerAdapter(list:MutableList<Fragment_eventos.ListaMenu>, context: Context){
        this.lista = list
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        binding = ListEventosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)

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

        binding.boton1.setOnClickListener{
            showMap(item.eventUri)

        }
        binding.apply {
            boton2.setOnClickListener{
                val sharedPreferences = context.getSharedPreferences("eventosApuntado", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("eventosApuntado", item.eventName)
                editor.apply()

                val direction = Fragment_eventosDirections.actionFragmentEventosToFragmentMisEventos(eventosApuntado = item.eventName.toString())
                holder.itemView.findNavController().navigate(direction)

            }
        }
    }


    override fun getItemCount(): Int {
        return lista.size

    }

    class ListViewHolder(val binding: ListEventosBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(lista: Fragment_eventos.ListaMenu) {}
    }


    fun showMap(geoLocation: String?) {

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoLocation))
        context.startActivity(intent)

    }

}
