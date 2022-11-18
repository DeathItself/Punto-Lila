package com.example.pantallasapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pantallasapp.databinding.LoginActivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var bin: LoginActivityBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bin = LoginActivityBinding.inflate(layoutInflater)
        setContentView(bin.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        bin.Registrarse.setOnClickListener {
            intent = Intent( this, Registro::class.java )
            startActivity(intent)

        }


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
