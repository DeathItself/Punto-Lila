package com.example.pantallasapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pantallasapp.databinding.FragmentEventosBinding


class fragment_eventos : Fragment() {
    private var _binding:FragmentEventosBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentEventosBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // binding.btnToast.setOnClickListener { Toast.makeText(activity, "click", Toast.LENGTH_SHORT).show() }
    }
}