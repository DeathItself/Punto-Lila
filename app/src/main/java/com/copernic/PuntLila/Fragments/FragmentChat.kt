package com.copernic.PuntLila.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.copernic.PuntLila.Adapters.MessageAdapter
import com.copernic.PuntLila.Models.Message
import com.copernic.PuntLila.R
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase



class FragmentChat : Fragment() {
    private var chatId = "" //Variable para almacenar el id del chat
    private var user = "" //Variable para almacenar el usuario actual

    private var db = Firebase.firestore

    private lateinit var messagesRecylerView: RecyclerView //Vista de RecyclerView para mostrar los mensajes
    private lateinit var sendMessageButton: Button //Botón para enviar un mensaje
    private lateinit var messageTextField: EditText //Campo de texto para escribir un mensaje

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        messagesRecylerView = view.findViewById(R.id.messagesRecylerView)
        sendMessageButton = view.findViewById(R.id.sendMessageButton)
        messageTextField = view.findViewById(R.id.messageTextField)

        chatId = arguments?.getString("chatId") ?: "" //Obtiene el id del chat desde los argumentos
        user =
            arguments?.getString("usuarios") ?: "" //Obtiene el usuario actual desde los argumentos

        if (chatId.isNotEmpty() && user.isNotEmpty()) {
            initViews()
        }
    }

    private fun initViews() {
        messagesRecylerView.layoutManager = LinearLayoutManager(requireContext())
        messagesRecylerView.adapter =
            MessageAdapter(user) //Asigna el adaptador para mostrar los mensajes

        sendMessageButton.setOnClickListener { sendMessage() } //Agrega un listener al botón para enviar un mensaje

        val chatRef = db.collection("chats").document(chatId) //Referencia a la colección de chats en Firestore

        chatRef.collection("messages").orderBy("dob", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { messages ->
                // Se obtienen los mensajes en una lista de objetos de tipo Message
                val listMessages = messages.toObjects(Message::class.java)
                // Se establecen los mensajes obtenidos en el adaptador del RecyclerView
                (messagesRecylerView.adapter as MessageAdapter).setData(listMessages)
            }
        // Se agrega un listener para escuchar los cambios en la colección de mensajes
        chatRef.collection("messages").orderBy("dob", Query.Direction.ASCENDING)
            .addSnapshotListener { messages, error ->
                // Si no hay error, se actualizan los mensajes en el adaptador
                if (error == null) {
                    messages?.let {
                        val listMessages = it.toObjects(Message::class.java)
                        (messagesRecylerView.adapter as MessageAdapter).setData(listMessages)
                    }
                }
            }
    }

    //Función que se encarga de enviar un mensaje al chat
    private fun sendMessage() {
        if (chatId.isNotEmpty()) {
            // Se crea un objeto de tipo Message con el mensaje y el usuario
            val message = Message(
                message = messageTextField.text.toString(), from = user
            )
            // Se agrega el mensaje a la colección de mensajes del chat
            db.collection("chats").document(chatId).collection("messages").add(message)
                .addOnSuccessListener {
                    // Se limpia el campo de texto una vez enviado el mensaje
                    messageTextField.setText("")
                }
                .addOnFailureListener {
                    // handle failure
                }
        }
    }
}
