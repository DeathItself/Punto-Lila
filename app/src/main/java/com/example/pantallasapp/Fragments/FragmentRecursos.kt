package com.example.pantallasapp.Fragments


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pantallasapp.databinding.FragmentRecursosBinding


class fragment_recursos : Fragment() {
    private var _bin: FragmentRecursosBinding? = null
    private val bin get() = _bin!!
    private val url = "https://www.terrassa.cat/es/com-muntar-un-punt-lila-"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _bin = FragmentRecursosBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bin.botonLila.setOnClickListener{
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(url)
            startActivity(openURL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bin = FragmentRecursosBinding.inflate(inflater, container, false)
        return bin.root
    }


}