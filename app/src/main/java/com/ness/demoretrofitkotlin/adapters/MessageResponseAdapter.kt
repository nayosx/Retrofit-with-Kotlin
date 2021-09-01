package com.ness.demoretrofitkotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ness.demoretrofitkotlin.R
import com.ness.demoretrofitkotlin.retrofit.domain.MessageResponse

class MessageResponseAdapter(private val messageList: ArrayList<MessageResponse>):
    RecyclerView.Adapter<MessageResponseAdapter.MessageResponseViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageResponseAdapter.MessageResponseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.simple_text_holder, parent, false)
        return MessageResponseViewHolder(view)
    }

    inner class MessageResponseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textViewHolder)

        fun bind(messageResponse: MessageResponse) {
            textView.text = messageResponse.title
        }
    }

    override fun onBindViewHolder(
        holder: MessageResponseAdapter.MessageResponseViewHolder,
        position: Int
    ) {
        holder.bind(messageList[position])
    }

    override fun getItemCount(): Int {
        return messageList.size
    }
}