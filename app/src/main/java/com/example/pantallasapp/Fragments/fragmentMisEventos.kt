package com.example.pantallasapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pantallasapp.R
import com.example.pantallasapp.databinding.FragmentMisEventosBinding


class fragmentMisEventos : Fragment() {

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
            binding.eventosApuntado.text = nombreEvento

    }

}