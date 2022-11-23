package com.example.pantallasapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.pantallasapp.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
                return true
            }
            R.id.charlas ->{
                Toast.makeText(applicationContext, "click on charlas", Toast.LENGTH_LONG).show()
                navController.navigate(R.id.action_global_fragment_charlas)
                return true
            }
            R.id.recursos ->{
                Toast.makeText(applicationContext, "click on recursos", Toast.LENGTH_LONG).show()
                navController.navigate(R.id.action_global_fragment_recursos)
                return true
            }
            R.id.puntos_lila ->{
                Toast.makeText(applicationContext, "click on puntos lila", Toast.LENGTH_LONG).show()
                navController.navigate(R.id.action_global_fragment_puntoslila)
                return true
            }
            R.id.activista ->{
                Toast.makeText(applicationContext, "click on activista", Toast.LENGTH_LONG).show()
                navController.navigate(R.id.action_global_fragment_solicitud_activista2)
                return true
            }
            R.id.ajustes_usuario ->{
                Toast.makeText(applicationContext, "click on ajustes usuario", Toast.LENGTH_LONG).show()
                navController.navigate(R.id.action_global_fragment_ajustes_usuario)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
