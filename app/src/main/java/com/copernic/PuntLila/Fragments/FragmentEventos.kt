package com.copernic.PuntLila.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.copernic.PuntLila.Adapters.ListAdapter
import com.copernic.PuntLila.R
import com.copernic.PuntLila.databinding.FragmentEventosBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FragmentEventos : Fragment() {
    private var _bin: FragmentEventosBinding? = null
    private val bin get() = _bin!!
    private val myAdapter: ListAdapter = ListAdapter()
    private val db = Firebase.firestore

    data class ListaMenu(val item: String,  val link: String, val photo: String) {
        var eventName: String? = null
        var eventPhoto: String? = null
        var eventUri: String? = null

        init {
            this.eventName = item
            this.eventPhoto = photo
            this.eventUri = link
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _bin = FragmentEventosBinding.inflate(inflater, container, false)
        return bin.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        bin.mostrarMyEvents.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_fragment_eventos_to_fragment_mis_eventos)
        }
        bin.apply {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        _bin = FragmentEventosBinding.inflate(layoutInflater)
        getList()
    }


    private fun initRecyclerView() {

        bin.ReciclerView.setHasFixedSize(true)

        bin.ReciclerView.layoutManager = LinearLayoutManager(context)


    }


    @SuppressLint("SuspiciousIndentation")
    private fun getList() {
        val lista: MutableList<ListaMenu> = arrayListOf()
            db.collection("Eventos")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        lista.add( ListaMenu(document["item"] as String,
                            document["link"] as String, document["photo"] as String
                        ))
                    }
                    myAdapter.ListaRecyclerAdapter(lista, requireContext())
                    bin.ReciclerView.adapter = myAdapter
                }
                .addOnFailureListener { exception ->
                    Log.w("FragmentEventos", "Error getting documents: ", exception)
                }

    }

}


