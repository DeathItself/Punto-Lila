package com.example.pantallasapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.pantallasapp.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class Registro : AppCompatActivity() {

    private lateinit var bin: ActivityRegistroBinding
    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    private companion object {
        private const val TAG = "Login"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bin = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(bin.root)

        auth = Firebase.auth


        bin.botonRegistro.setOnClickListener {
            crearUsuari(
                bin.correoId.text.toString(),
                bin.Nombre.text.toString(),
                bin.contraId.text.toString()
            )
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        currentUser?.let {
            reload();
        }
    }

    private fun reload() {
        val user = auth.currentUser

        user?.let {
            val nom = user.displayName ?:run{"sense nom"}
            bin.Nombre.setHint("Usuari email: ${user.email}\n$nom")
        } ?: run {
            bin.Nombre.setHint("Usuari: no assignat")
        }
    }

    private fun crearUsuari(email: String, nom: String, password: String) {
        Log.d(TAG,"Creacion usuario: $email, $nom, $password")
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    if( nom.length > 1 ) posaNomUser( nom )
                    goHome()
                } else {

                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    reload()
                }
            }
    }


    private fun posaNomUser( nom: String ) {
        val profileUpdates = userProfileChangeRequest {
            displayName = nom
        }

        auth.currentUser!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User profile updated.")
                    agregarDatos()
                    reload()

                }
            }

    }

    private fun agregarDatos() {
            val userdates = hashMapOf(
                "name" to bin.Nombre.text.toString(),
                "password" to bin.contraId.text.toString()
            )

            db.collection("usuarios").document(auth.currentUser!!.uid).set(userdates)
                .addOnSuccessListener { Log.d("TAG", "Se ha guardado correctamente") }
                .addOnFailureListener{e -> Log.w("TAG", "error $e")

                }
        }
    private fun goHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    }




/*
db.collection("users").document(email).set(
hashMapOf("name" to bin.Nombre.text.toString(),
"password" to bin.contraId.text.toString())
)*/