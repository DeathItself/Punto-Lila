package com.copernic.PuntLila.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.copernic.PuntLila.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class Registro : AppCompatActivity() {

    /*En este código se están declarando tres variables:

        "bin" es una variable de tipo "ActivityRegistroBinding", que se utiliza para enlazar la vista de la actividad con el controlador.
        "auth" es una variable de tipo "FirebaseAuth", que se utiliza para autenticar al usuario en Firebase.
        "db" es una instancia de la clase "FirebaseFirestore", que se utiliza para conectarse a la base de datos de Firebase Firestore.*/
    private lateinit var bin: ActivityRegistroBinding
    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    /*Se está declarando un objeto de compañero privado llamado "companion object" dentro de la clase.
     Dentro de ese objeto de compañero,
     se está declarando una variable constante llamada "TAG" con el valor "Login".*/
    private companion object {
        private const val TAG = "Login"
    }

    /*
        Se llama al método "super.onCreate(savedInstanceState)" para ejecutar el código de la función "onCreate" de la clase padre.
        Se esta asignando el valor de la variable "bin" como una instancia de la clase "ActivityRegistroBinding" inflando el layout con el metodo inflate.
        Se establece la vista de la actividad con el método "setContentView" con el valor bin.root.
        Se asigna el valor de la variable "auth" como una instancia de la clase "FirebaseAuth"
        Se establece un listener al boton bin.botonRegistro,
        al hacer clic en el boton se ejecuta la funcion "crearUsuari" y
        se pasan como parametros los valores obtenidos de los editText bin.correoId.text.toString(), bin.Nombre.text.toString(),
        bin.contraId.text.toString() respectivamente.
     */
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

        }
    }


    private fun crearUsuari(email: String, nom: String, password: String) {
        Log.d(TAG, "Creacion usuario: $email, $nom, $password")
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    if (nom.length > 1) posaNomUser(nom)
                    goHome()
                } else {

                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()

                }
            }
    }


    private fun posaNomUser(nom: String) {
        val profileUpdates = userProfileChangeRequest {
            displayName = nom
        }

        auth.currentUser!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User profile updated.")
                    agregarDatos()


                }
            }

    }

    private fun agregarDatos() {
        val userdates = hashMapOf(
            "email" to bin.correoId.text.toString(),
            "name" to bin.Nombre.text.toString(),
            "password" to bin.contraId.text.toString()
        )

        db.collection("usuarios").document(auth.currentUser!!.uid).set(userdates)
            .addOnSuccessListener { Log.d("TAG", "Se ha guardado correctamente") }
            .addOnFailureListener { e ->
                Log.w("TAG", "error $e")

            }
    }

    private fun goHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}