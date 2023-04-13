package com.example.realtime.chat

import android.content.Context
import android.os.Message
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.realtime.R
import java.text.SimpleDateFormat
import java.util.*


class Message_Adapter(
    private val context: Context,
    private val messages: List<MessageTo>,
    private val currentUserUid: String
) : RecyclerView.Adapter<Message_Adapter.MessageViewHolder>() {

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageText: TextView = itemView.findViewById(R.id.message_text)
      //  val timestampTextView:TextView = itemView.findViewById(R.id.timestamp_text_view)
//
//
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.message_item, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]

       holder.messageText.text=message.text

        val layoutParams = holder.messageText.layoutParams as
                LinearLayout.LayoutParams
      layoutParams.gravity = if (message.senderId == currentUserUid)
           Gravity.END else Gravity.START
        holder.messageText.layoutParams = layoutParams

        val sdf = SimpleDateFormat("MMM dd, yyyy h:mm a", Locale.getDefault())
//        sdf.timeZone=TimeZone.getDefault()
//        val dateString = sdf.format(Date(message.timestamp))
//
//        holder.timestampTextView.text = dateString
//            if (message.senderId==senderUid){
//                messages.setBackgroundResource(R.drawable.chat_outgoing_message_background)
//           }else{
//                messageTextView.setBackgroundResource(R.drawable.chat_incoming_message_background)
//           }
//        }

    }

    override fun getItemCount(): Int {
        return messages.size
    }
}