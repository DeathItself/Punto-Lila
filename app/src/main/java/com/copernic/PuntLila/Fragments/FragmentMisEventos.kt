package com.copernic.PuntLila.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.copernic.PuntLila.R
import com.copernic.PuntLila.databinding.FragmentMisEventosBinding


class FragmentMisEventos : Fragment() {

    private var _binding: FragmentMisEventosBinding? = null

    private val binding get() = _binding!!

    private var nombreEvento: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            arguments?.let {
                nombreEvento = it.getString("eventosApuntado")
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

        val sharedPreferences = requireContext().getSharedPreferences("eventosApuntado", Context.MODE_PRIVATE)
        nombreEvento = sharedPreferences.getString("eventosApuntado", "")
        binding.eventosApuntado.text = nombreEvento
    }


}