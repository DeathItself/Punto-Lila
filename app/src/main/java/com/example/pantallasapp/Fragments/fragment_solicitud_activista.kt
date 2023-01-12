package com.example.pantallasapp.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.pantallasapp.Activity.MainActivity.Companion.CHANNEL_ID
import com.example.pantallasapp.R
import com.example.pantallasapp.databinding.FragmentEventosBinding
import com.example.pantallasapp.databinding.FragmentSolicitudActivistaBinding

class fragment_solicitud_activista : Fragment() {
    private var _bin: FragmentSolicitudActivistaBinding? = null
    private val bin get() = _bin!!
    private val _url = "https://forms.gle/ZNbssPTaP41rMzFA9"
    private val notificationId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _bin = FragmentSolicitudActivistaBinding.inflate(layoutInflater)

        var builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID).also {
            it.setSmallIcon(R.drawable.splash)
            it.setContentTitle(getString(R.string.channel_name))
            it.setContentText(getString(R.string.channel_description))
            it.setPriority(NotificationCompat.PRIORITY_HIGH)
        }.build()

        val notification = NotificationManagerCompat.from(requireContext())

        bin.EnviarFormulari.setOnClickListener{
            notification.notify(notificationId, builder)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bin.EnviarFormulari.setOnClickListener{
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(_url)
            startActivity(openURL)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bin = FragmentSolicitudActivistaBinding.inflate(inflater, container, false)
        return bin.root
    }

    companion object {

    }
}