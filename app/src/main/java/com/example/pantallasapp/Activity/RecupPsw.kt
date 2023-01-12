package com.example.pantallasapp.Activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pantallasapp.R
import com.google.firebase.auth.FirebaseAuth

class RecupPsw : AppCompatActivity() {
    private var etEmail: EditText? = null
    private var btnSend: Button? = null
    //Firebase references
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recup_passw_activity)
        initialise()
    }
    private fun initialise() {
        etEmail = findViewById<View>(R.id.RecuperarCorreo) as EditText
        btnSend = findViewById<View>(R.id.RecuperarCuenta) as Button
        mAuth = FirebaseAuth.getInstance()
        btnSend!!.setOnClickListener { sendPasswordResetEmail() }
    }
    private fun sendPasswordResetEmail() {
        val email = etEmail?.text.toString()
        if (!TextUtils.isEmpty(email)) {
            mAuth!!
                .sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Email Enviado", Toast.LENGTH_SHORT).show()
                        goMain()
                    } else {
                        Toast.makeText(this, "No se encontró el usuario con este correo", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, "Agregue el correo", Toast.LENGTH_SHORT).show()
        }
    }
    private fun goMain() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}