package com.example.amorzinho

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.button.MaterialButton

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        try {
            val btnQuiz = findViewById<MaterialButton>(R.id.btn_quiz)
            btnQuiz.setOnClickListener {
                Log.d("HomeActivity", "Botão Quiz clicado")
                startMainActivity(0)
            }

            val btnTimeline = findViewById<MaterialButton>(R.id.btn_timeline)
            btnTimeline.setOnClickListener {
                Log.d("HomeActivity", "Botão Timeline clicado")
                startMainActivity(1)
            }

            val btnGallery = findViewById<MaterialButton>(R.id.btn_gallery)
            btnGallery.setOnClickListener {
                Log.d("HomeActivity", "Botão Gallery clicado")
                startMainActivity(2)
            }

            val btnMessages = findViewById<MaterialButton>(R.id.btn_messages)
            btnMessages.setOnClickListener {
                Log.d("HomeActivity", "Botão Messages clicado")
                startMainActivity(3)
            }
        } catch (e: Exception) {
            Log.e("HomeActivity", "Erro ao configurar botões", e)
            Toast.makeText(this, "Erro ao inicializar: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun startMainActivity(tabIndex: Int) {
        try {
            Log.d("HomeActivity", "Iniciando MainActivity com tab: $tabIndex")
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("tab_index", tabIndex)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
            finish() // Fecha a HomeActivity para não ficar na pilha
            Log.d("HomeActivity", "MainActivity iniciada com sucesso")
        } catch (e: Exception) {
            Log.e("HomeActivity", "Erro ao iniciar MainActivity", e)
            Toast.makeText(this, "Erro ao abrir a tela: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}