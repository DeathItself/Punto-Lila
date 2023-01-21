package com.copernic.PuntLila.Activity


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.copernic.PuntLila.R
import com.copernic.PuntLila.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth

    lateinit var binding: ActivityMainBinding



    companion object {
        val CHANNEL_ID = "pantallasApp"
        val PREF_NAME = "datosusuario"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        createNotificationChannel()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.myNavHostFragment)
        return when (item.itemId) {


            R.id.eventos -> {
                Toast.makeText(applicationContext, "click on eventos", Toast.LENGTH_LONG).show()
                navController.navigate(R.id.action_global_fragment_eventos)
                supportActionBar!!.title = "Eventos"
                return true
            }
            R.id.recursos -> {
                Toast.makeText(applicationContext, "click on recursos", Toast.LENGTH_LONG).show()
                navController.navigate(R.id.action_global_fragment_recursos)
                supportActionBar!!.title = "Recursos"
                return true
            }
            R.id.activista -> {
                Toast.makeText(applicationContext, "click on activista", Toast.LENGTH_LONG).show()
                navController.navigate(R.id.action_global_fragment_solicitud_activista2)
                supportActionBar!!.title = "Solicitud Activista"
                return true
            }
            R.id.mis_eventos -> {
                Toast.makeText(applicationContext, "click on mis eventos", Toast.LENGTH_LONG).show()
                navController.navigate(R.id.action_global_fragment_Mis_Eventos)
                supportActionBar!!.title = "Mis Eventos"
                return true
            }
            R.id.logout -> {
                if (auth.currentUser != null) {
                    val prefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                    val editor = prefs.edit()
                    editor.putString("emailusuario", auth.currentUser!!.email)
                    editor.commit()
                }
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
                lightColor = Color.BLUE
                enableLights(true)
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


}
