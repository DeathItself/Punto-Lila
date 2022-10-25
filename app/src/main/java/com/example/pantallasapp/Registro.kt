package com.example.pantallasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.pantallasapp.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class Registro : AppCompatActivity() {

    private lateinit var bin: ActivityRegistroBinding
    private lateinit var auth: FirebaseAuth

    private companion object {
        private const val TAG = "Login"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bin = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(bin.root)

        // Initialize Firebase Auth
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
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        currentUser?.let {
            reload();
        }
    }

    private fun reload() { //quan canviem/entrem en un usuari amb l'aplicació
        val user = auth.currentUser

        user?.let {
            val nom = user.displayName ?:run{"sense nom"}
            bin.Nombre.setHint("Usuari email: ${user.email}\n$nom")
        } ?: run {
            bin.Nombre.setHint("Usuari: no assignat")
        }
    }

    private fun crearUsuari(email: String, nom: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    if( nom.length > 1 ) posaNomUser( nom )
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    reload()
                }
            }
    }

    // https://firebase.google.com/docs/auth/android/manage-users --> com canviar altres parametres
    private fun posaNomUser( nom: String ) { //canvia el perfil de l'usuari
        val profileUpdates = userProfileChangeRequest {
            displayName = nom
            //photoUri = Uri.parse("https://example.com/jane-q-user/profile.jpg") //si calgués canviar la foto
        }

        auth.currentUser!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User profile updated.")
                    reload()
                } //es podria fer alguna cosa si donés error al canviar
            }
    }

    }