package com.example.pantallasapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pantallasapp.databinding.FragmentCharlasBinding


class fragment_charlas : Fragment() {
    private var _binding: FragmentCharlasBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharlasBinding.inflate(inflater, container, false)
        return binding.root
    }
    }


