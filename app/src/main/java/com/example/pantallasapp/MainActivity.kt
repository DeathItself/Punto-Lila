package com.example.pantallasapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pantallasapp.databinding.ActivityMainBinding
import com.example.pantallasapp.ui.ReciclerView.ListAdapter
import com.example.pantallasapp.ui.ReciclerView.Menu
import com.example.pantallasapp.ui.ReciclerView.ReciclerInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var bin: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bin = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bin.root)

        // Initialize Firebase Auth
        auth = Firebase.auth
        initRecyclerView()

        bin.Registrarse.setOnClickListener {
            intent = Intent( this, Registro::class.java )
            startActivity(intent)

        }


    }

    private fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.reciclerMenu)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ListAdapter(ReciclerInfo.ValorLista)
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        reload()
    }

    private fun reload() { //quan canviem/entrem en un usuari amb l'aplicació
        val user = auth.currentUser

        user?.let {
            val nom = {"sense nom"}
            bin.Registrarse.setHint("Usuari email: ${user.email}\n$nom")
        } ?: run {
            bin.Registrarse.setHint("Usuari: no assignat")
        }
    }
}
