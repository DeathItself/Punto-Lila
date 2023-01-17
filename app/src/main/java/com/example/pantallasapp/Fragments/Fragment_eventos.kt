package com.example.pantallasapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pantallasapp.Adapters.ListAdapter
import com.example.pantallasapp.R
import com.example.pantallasapp.databinding.FragmentEventosBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class Fragment_eventos : Fragment() {
    private var _bin: FragmentEventosBinding? = null
    private val bin get() = _bin!!
    private val myAdapter: ListAdapter = ListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _bin = FragmentEventosBinding.inflate(inflater, container, false)
        return bin.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        val db = FirebaseFirestore.getInstance()
        val query = db.collection("Eventos")
        query.get().addOnSuccessListener { result ->
            val lista: MutableList<ListaMenu> = arrayListOf()
            for (document in result) {
                val item = document.toObject(ListaMenu::class.java)
                lista.add(item)
            }
            myAdapter.ListaRecyclerAdapter(lista, requireContext())
            myAdapter.notifyDataSetChanged()
        }
            bin.mostrarMyEvents.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_fragment_eventos_to_fragment_mis_eventos)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        _bin = FragmentEventosBinding.inflate(layoutInflater)

    }


    private fun initRecyclerView(){

        bin.ReciclerView.setHasFixedSize(true)

        bin.ReciclerView.layoutManager =  LinearLayoutManager(context)

        myAdapter.ListaRecyclerAdapter(getList(), requireContext() )

        bin.ReciclerView.adapter = myAdapter

    }


    private fun getList(): MutableList<ListaMenu>{
        val lista: MutableList<ListaMenu> = arrayListOf()
        return lista
    }

    data class ListaMenu(val item: String, val photo: String, val link: String) {
        var eventName: String? = null
        var eventPhoto: String? = null
        var eventUri: String? = null

        init {
            this.eventName = item
            this.eventPhoto = photo
            this.eventUri = link
        }
    }

}


