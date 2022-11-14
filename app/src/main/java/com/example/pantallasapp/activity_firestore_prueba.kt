package com.example.pantallasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pantallasapp.databinding.ActivityFirestorePruebaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase

class FirestorePrueba : AppCompatActivity() {

//exemples en https://github.com/firebase/snippets-android/blob/1567240e90103264b4b027b451e3de634f934db6/firestore/app/src/main/java/com/google/example/firestore/kotlin/DocSnippets.kt

    private lateinit var bin: ActivityFirestorePruebaBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    companion object {
        private const val TAG = "FirestorePrueba"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bin = ActivityFirestorePruebaBinding.inflate(layoutInflater)
        setContentView(bin.root)

        // Initialize Firebase Auth
        auth = Firebase.auth
        db = Firebase.firestore

        bin.btProvaExempleFirestore.setOnClickListener {
            Log.d( TAG, "executa exemple")
            setup()
            exemple()
        }
    }

    private fun exemple() {
        val db = Firebase.firestore
        Log.d( TAG, "newDocument")
        newDocument()
        Log.d( TAG, "exampleData")
        exampleData()
        Log.d( TAG, "exampleDataCollectionGroup")
        exampleDataCollectionGroup()

        //updateDocument()
    }

    private fun setup() {
        // [START set_firestore_settings]
        val settings = firestoreSettings {
            isPersistenceEnabled = true
        }
        db.firestoreSettings = settings
        // [END set_firestore_settings]
    }

    private fun newDocument() {
        // [START new_document]
        val data = HashMap<String, Any>()

        val newCityRef = db.collection("cities").document()

        // Later...
        newCityRef.set(data)
        // [END new_document]

    }

    private fun updateDocument() {
        // [START update_document]
        val washingtonRef = db.collection("cities").document("DC")

        // Set the "isCapital" field of the city 'DC'
        washingtonRef
            .update("capital", true)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
        // [END update_document]
    }

    private fun exampleData() {
        // [START example_data]
        val cities = db.collection("cities")

        val data1 = hashMapOf(
            "name" to "San Francisco",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 860000,
            "regions" to listOf("west_coast", "norcal")
        )
        cities.document("SF").set(data1)

        val data2 = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 3900000,
            "regions" to listOf("west_coast", "socal")
        )
        cities.document("LA").set(data2)

        val data3 = hashMapOf(
            "name" to "Washington D.C.",
            "state" to null,
            "country" to "USA",
            "capital" to true,
            "population" to 680000,
            "regions" to listOf("east_coast")
        )
        cities.document("DC").set(data3)

        val data4 = hashMapOf(
            "name" to "Tokyo",
            "state" to null,
            "country" to "Japan",
            "capital" to true,
            "population" to 9000000,
            "regions" to listOf("kanto", "honshu")
        )
        cities.document("TOK").set(data4)

        val data5 = hashMapOf(
            "name" to "Beijing",
            "state" to null,
            "country" to "China",
            "capital" to true,
            "population" to 21500000,
            "regions" to listOf("jingjinji", "hebei")
        )
        cities.document("BJ").set(data5)
        // [END example_data]
    }

    fun exampleDataCollectionGroup() {
        // [START fs_collection_group_query_data_setup]
        val citiesRef = db.collection("cities")

        val ggbData = mapOf(
            "name" to "Golden Gate Bridge",
            "type" to "bridge"
        )
        citiesRef.document("SF").collection("landmarks").add(ggbData)

        val lohData = mapOf(
            "name" to "Legion of Honor",
            "type" to "museum"
        )
        citiesRef.document("SF").collection("landmarks").add(lohData)

        val gpData = mapOf(
            "name" to "Griffth Park",
            "type" to "park"
        )
        citiesRef.document("LA").collection("landmarks").add(gpData)

        val tgData = mapOf(
            "name" to "The Getty",
            "type" to "museum"
        )
        citiesRef.document("LA").collection("landmarks").add(tgData)

        val lmData = mapOf(
            "name" to "Lincoln Memorial",
            "type" to "memorial"
        )
        citiesRef.document("DC").collection("landmarks").add(lmData)

        val nasaData = mapOf(
            "name" to "National Air and Space Museum",
            "type" to "museum"
        )
        citiesRef.document("DC").collection("landmarks").add(nasaData)

        val upData = mapOf(
            "name" to "Ueno Park",
            "type" to "park"
        )
        citiesRef.document("TOK").collection("landmarks").add(upData)

        val nmData = mapOf(
            "name" to "National Musuem of Nature and Science",
            "type" to "museum"
        )
        citiesRef.document("TOK").collection("landmarks").add(nmData)

        val jpData = mapOf(
            "name" to "Jingshan Park",
            "type" to "park"
        )
        citiesRef.document("BJ").collection("landmarks").add(jpData)

        val baoData = mapOf(
            "name" to "Beijing Ancient Observatory",
            "type" to "musuem"
        )
        citiesRef.document("BJ").collection("landmarks").add(baoData)
        // [END fs_collection_group_query_data_setup]
    }
}
