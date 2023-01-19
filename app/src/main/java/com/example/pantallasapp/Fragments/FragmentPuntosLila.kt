package com.example.pantallasapp.Fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pantallasapp.Adapters.ChatAdapter
import com.example.pantallasapp.R
import com.example.pantallasapp.models.Chat
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class FragmentPuntosLila : Fragment() {

    private var user = ""
    private lateinit var listChatsRecyclerView: RecyclerView
    private lateinit var newChatButton: Button
    private lateinit var newChatText: EditText

    private var db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_puntoslila, container, false)
        user = requireArguments().getString("user").toString()
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        listChatsRecyclerView = view.findViewById(R.id.listChatsRecyclerView)
        newChatButton = view.findViewById(R.id.newChatButton)
        newChatText = view.findViewById(R.id.newChatText)

        newChatButton.setOnClickListener { newChat() }

        listChatsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        listChatsRecyclerView.adapter =
            ChatAdapter { chat ->
                chatSelected(chat)
            }

        val userRef = db.collection("usuarios").document(user)

        userRef.collection("chats")
            .get()
            .addOnSuccessListener { chats ->
                val listChats = chats.toObjects(Chat::class.java)

                (listChatsRecyclerView.adapter as ChatAdapter).setData(listChats)
            }

        userRef.collection("chats")
            .addSnapshotListener { chats, error ->
                if (error == null) {
                    chats?.let {
                        val listChats = it.toObjects(Chat::class.java)

                        (listChatsRecyclerView.adapter as ChatAdapter).setData(listChats)
                    }
                }
            }
    }

    private fun chatSelected(chat: Chat) {
        val bundle = bundleOf("chatId" to chat.id, "user" to user)
        val fragmentChat = FragmentChat()
        fragmentChat.arguments = bundle
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.messagesRecylerView, fragmentChat)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun newChat() {
        val otherUser = newChatText?.text.toString()
        if (otherUser.isBlank()) {
            Toast.makeText(requireContext(), "Ingrese un usuario válido", Toast.LENGTH_SHORT).show()
            return
        }
        val chatId = UUID.randomUUID().toString()
        val users = listOf(user, otherUser)
        val chat = Chat(
            id = chatId,
            name = "Chat con $otherUser",
            users = users
        )
        db.collection("usuarios").document(user).collection("chats").document(chatId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    Toast.makeText(requireContext(), "Ya existe un chat con ese usuario", Toast.LENGTH_SHORT).show()
                    return@addOnSuccessListener
                }
                db.collection("chats").document(chatId).set(chat)
                db.collection("usuarios").document(user).collection("chats").document(chatId).set(chat)
                db.collection("usuarios").document(otherUser).collection("chats").document(chatId).set(chat)

                val bundle = bundleOf("chatId" to chatId, "user" to user)
                val fragmentChat = FragmentChat()
                fragmentChat.arguments = bundle
                val fragmentTransaction = parentFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.listChatsRecyclerView, fragmentChat)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(requireContext(), "Error al crear el chat, por favor intente de nuevo", Toast.LENGTH_SHORT).show()
                Log.e("New Chat Error", exception.toString())
            }
    }
    }
