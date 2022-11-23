<<<<<<< HEAD:app/src/main/java/com/example/pantallasapp/Activity/LoginActivity.kt
package com.example.pantallasapp.Activity
=======
package com.example.pantallasapp.ui.Activities
>>>>>>> 12cf45b1ad3c48137490bc7f8be5daa3fcd4a4be:app/src/main/java/com/example/pantallasapp/ui/Activities/MainActivity.kt

import android.content.Intent
import android.net.Uri
import android.os.Bundle
<<<<<<< HEAD:app/src/main/java/com/example/pantallasapp/Activity/LoginActivity.kt
import com.example.pantallasapp.databinding.LoginActivityBinding
=======
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.pantallasapp.R
import com.example.pantallasapp.databinding.ActivityMainBinding
>>>>>>> 12cf45b1ad3c48137490bc7f8be5daa3fcd4a4be:app/src/main/java/com/example/pantallasapp/ui/Activities/MainActivity.kt
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var bin: LoginActivityBinding
    private lateinit var auth: FirebaseAuth
    val uri = Uri.parse("geo:41.56602039747692, 2.011540981764755")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bin = LoginActivityBinding.inflate(layoutInflater)
        setContentView(bin.root)

        // Initialize Firebase Auth
        auth = Firebase.auth
<<<<<<< HEAD:app/src/main/java/com/example/pantallasapp/Activity/LoginActivity.kt
=======
        bin.mapaId.setOnClickListener{
           showMap(uri)
        }
      //  initRecyclerView()
>>>>>>> 12cf45b1ad3c48137490bc7f8be5daa3fcd4a4be:app/src/main/java/com/example/pantallasapp/ui/Activities/MainActivity.kt

       // bin.Registrarse.setOnClickListener {
       //     intent = Intent( this, Registro::class.java )
       //     startActivity(intent) }




<<<<<<< HEAD:app/src/main/java/com/example/pantallasapp/Activity/LoginActivity.kt
=======
    }
    //esto está pendiente de cambio por eso sigue el findViewById
   // private fun initRecyclerView(){
        //val recyclerView = findViewById<RecyclerView>(R.id.reciclerMenu)
       // recyclerView.layoutManager = LinearLayoutManager(this)
       // recyclerView.adapter = ListAdapter(ReciclerInfo.ValorLista) }
>>>>>>> 12cf45b1ad3c48137490bc7f8be5daa3fcd4a4be:app/src/main/java/com/example/pantallasapp/ui/Activities/MainActivity.kt


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
           // reload();
        }
    }


    fun showMap(geoLocation: Uri) {

            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = geoLocation
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }

    }

    private fun updateUI(user: FirebaseUser?) {
       // reload()
    }

    private fun reload() { //quan canviem/entrem en un usuari amb l'aplicació
        val user = auth.currentUser

        user?.let {
            val nom = {"sense nom"}
           // bin.Registrarse.setHint("Usuari email: ${user.email}\n$nom")
        } ?: run {
           // bin.Registrarse.setHint("Usuari: no assignat")
        }
    }


}
