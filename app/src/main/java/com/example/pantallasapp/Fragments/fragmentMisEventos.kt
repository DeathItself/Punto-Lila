package com.example.pantallasapp.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pantallasapp.R
import com.example.pantallasapp.databinding.FragmentEventosBinding
import com.example.pantallasapp.databinding.FragmentMisEventosBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class fragmentMisEventos : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentMisEventosBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflater.inflate(R.layout.fragment_mis_eventos, container, false)
        _binding = FragmentMisEventosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments

        if ( bundle == null){
            Log.d("Confirmation", "Mis eventos no ha recibido info" )
            return
        }

        val args = fragmentMisEventosArgs.fromBundle(bundle)

      //  if (args.name.isNullOrBlank()){
      //      binding.EventosApuntado.text = getString(R.id.boton1)

      //  } else {
      //      binding.EventosApuntado.text = args.name
      //  }
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            fragmentMisEventos().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}