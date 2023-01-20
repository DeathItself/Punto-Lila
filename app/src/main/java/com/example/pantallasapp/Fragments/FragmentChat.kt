package com.example.pantallasapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pantallasapp.Adapters.MessageAdapter
import com.example.pantallasapp.R
import com.example.pantallasapp.models.Message
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class FragmentChat : Fragment() {

    private var chatId = ""
    private var user = ""

    private lateinit var messagesRecylerView: RecyclerView
    private lateinit var sendMessageButton: Button
    private lateinit var messageTextField: EditText

    private var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        messagesRecylerView = view.findViewById(R.id.messagesRecylerView)
        messagesRecylerView.layoutManager = LinearLayoutManager(context)
        messagesRecylerView.adapter = MessageAdapter(user)

        sendMessageButton = view.findViewById(R.id.sendMessageButton)
        messageTextField = view.findViewById(R.id.messageTextField)

        sendMessageButton.setOnClickListener { sendMessage() }

        val chatRef = db.collection("chats").document(chatId)

        chatRef.collection("messages").orderBy("dob", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { messages ->
                val listMessages = messages.toObjects(Message::class.java)
                (messagesRecylerView.adapter as MessageAdapter).setData(listMessages)
            }

        chatRef.collection("messages").orderBy("dob", Query.Direction.ASCENDING)
            .addSnapshotListener { messages, error ->
                if (error == null) {
                    messages?.let {
                        val listMessages = it.toObjects(Message::class.java)
                        (messagesRecylerView.adapter as MessageAdapter).setData(listMessages)
                    }
                }
            }
    }

    private fun sendMessage() {
        val message = Message(
            message = messageTextField.text.toString(),from = user)
        db.collection("chats").document(chatId).collection("messages").document().set(message)

        messageTextField.setText("")
    }
}
