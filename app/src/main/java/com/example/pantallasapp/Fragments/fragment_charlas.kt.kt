package com.example.pantallasapp.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pantallasapp.databinding.FragmentCharlasBinding


class FragmentCharlas : Fragment() {
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


