package com.example.amorzinho

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnQuiz: Button = findViewById(R.id.btn_quiz)
        btnQuiz.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).apply {
                putExtra("tab_index", 0)
            })
        }

        val btnTimeline: Button = findViewById(R.id.btn_timeline)
        btnTimeline.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).apply {
                putExtra("tab_index", 1)
            })
        }

        val btnGallery: Button = findViewById(R.id.btn_gallery)
        btnGallery.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).apply {
                putExtra("tab_index", 2)
            })
        }

        val btnMessages: Button = findViewById(R.id.btn_messages)
        btnMessages.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).apply {
                putExtra("tab_index", 3)
            })
        }
    }
}