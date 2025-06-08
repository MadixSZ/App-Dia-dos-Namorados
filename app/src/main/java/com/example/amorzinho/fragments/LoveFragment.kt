package com.example.amorzinho.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.amorzinho.R

class LoveFragment : Fragment(R.layout.activity_fragment_love) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loveMessages = resources.getStringArray(R.array.love_messages)
        val messageView = view.findViewById<TextView>(R.id.messageTv)
        val button = view.findViewById<Button>(R.id.newMessageBtn)

        button.setOnClickListener {
            messageView.text = loveMessages.random()
        }
    }
}