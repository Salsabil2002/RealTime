package com.example.realtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.realtime.chat.MessageTo
import com.example.realtime.chat.Message_Adapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase



class MainActivity : AppCompatActivity() {

    private  lateinit var messagesRecyclerView: RecyclerView
    private lateinit var messageEditText: EditText
    private lateinit var sendButton: Button
    private lateinit var senderUid:String
    private lateinit var resiverUid:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        messagesRecyclerView=findViewById(R.id.messages_recycler_view)
        messageEditText=findViewById(R.id.message_input)
        sendButton=findViewById(R.id.send_button)

        senderUid="j43qbnoS6bXvR6thY4SufJj0BDC2"
        resiverUid="hJhzxv1ytTh6zLTFdRaPVPuQl0q2"

        sendButton.setOnClickListener {
            val messageText = messageEditText.text.toString().trim()
            if(messageText.isNotEmpty()){
                sendmessage(messageText)
                messageEditText.setText("")
            }

        }

        val messageList = mutableListOf<MessageTo>()
        val messageAdapter = Message_Adapter(this,messageList,senderUid)
        messagesRecyclerView.layoutManager= LinearLayoutManager(this)
        messagesRecyclerView.adapter=messageAdapter
        FirebaseDatabase.getInstance().getReference("chat")
            .addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val message  =  snapshot.getValue(MessageTo::class.java)
                    if (message != null){
                        messageList.add(message)

                    }

                    messageAdapter.notifyItemInserted(messageList.size - 1)

                    messagesRecyclerView.scrollToPosition(messageList.size - 1)
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

                }

                override fun onChildRemoved(snapshot: DataSnapshot) {

                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

    }
    fun sendmessage(messageText:String){
        val timestamp = System.currentTimeMillis()
        val message = MessageTo(messageText,senderUid,resiverUid,timestamp)
        FirebaseDatabase.getInstance().getReference("chat").push().setValue(message)
    }
}