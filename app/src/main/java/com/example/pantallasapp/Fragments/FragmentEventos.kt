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

class FragmentEventos : Fragment() {
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
        bin.mostrarMyEvents.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_fragment_eventos_to_fragment_mis_eventos)
        }
        bin.apply {

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
        lista.add(
            ListaMenu(
                "Concierto Techno",
                "https://media.resources.festicket.com/www/magazine/Techno_L_UiNUh4Q.jpg",
                "geo:41.567067, 1.999756"
            )
        )
        lista.add(
           ListaMenu(
                "Festa Major Terrassa",
                "https://www.alleventshotels.com/wp-content/uploads/2018/08/festa-major.jpg",
                "geo:41.578669, 2.035128"
            )
        )
        lista.add(
           ListaMenu(
                "Taller modernista",
                "https://www.ruidophoto.com/media/post/Taller5-10-5_E5Wg3Z0.jpg",
                "geo:41.582542, 2.012846"
            )
        )

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


