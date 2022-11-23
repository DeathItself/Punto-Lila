package com.example.pantallasapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pantallasapp.R


class Fragment_eventos : Fragment() {

    data class ListaMenu(val item: String, val photo: String) {
        var eventName: String? = null
        var eventPhoto: String? = null

        init {
            this.eventName = item
            this.eventPhoto = photo
        }
    }
}