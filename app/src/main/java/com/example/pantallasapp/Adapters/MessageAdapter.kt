package com.example.pantallasapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.pantallasapp.models.Message
import com.example.pantallasapp.R

class MessageAdapter(private val user: String) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    private var messages: List<Message> = emptyList()


    fun setData(list: List<Message>) {
        messages = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_item_mensaje,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]

        if (user == message.from) {
            holder.myMessageLayout.visibility = View.VISIBLE
            holder.otherMessageLayout.visibility = View.GONE

            holder.myMessageTextView.text = message.message
        } else {
            holder.myMessageLayout.visibility = View.GONE
            holder.otherMessageLayout.visibility = View.VISIBLE

            holder.othersMessageTextView.text = message.message
        }

    }

    override fun getItemCount(): Int {
        return messages.size
    }

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val myMessageLayout: View = itemView.findViewById(R.id.myMessageLayout)
        val otherMessageLayout: View = itemView.findViewById(R.id.otherMessageLayout)
        val myMessageTextView: TextView = itemView.findViewById(R.id.myMessageTextView)
        val othersMessageTextView: TextView = itemView.findViewById(R.id.othersMessageTextView)
    }
}
