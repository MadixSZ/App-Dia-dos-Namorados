package com.example.amorzinho

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.amorzinho.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configuração da Toolbar
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "App do Amor"
        }

        try {
            val tabIndex = intent.getIntExtra("tab_index", 0)
            Log.d("MainActivity", "Índice da aba recebido: $tabIndex")

            val viewPager = findViewById<ViewPager2>(R.id.viewPager)
            val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

            viewPager.adapter = ViewPagerAdapter(this)
            Log.d("MainActivity", "ViewPager configurado")

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when(position) {
                    0 -> "Quiz"
                    1 -> "Timeline"
                    2 -> "Mural"
                    3 -> "Mensagens"
                    else -> null
                }
            }.attach()
            Log.d("MainActivity", "TabLayout configurado")

            viewPager.setCurrentItem(tabIndex, false)
            Log.d("MainActivity", "Aba definida: $tabIndex")

        } catch (e: Exception) {
            Log.e("MainActivity", "Erro na inicialização", e)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        // Volta para a HomeActivity
        startActivity(Intent(this, HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        })
        finish()
        return true
    }

    override fun onResume() {
        super.onResume()
        // Garante que o botão de voltar está sempre visível
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}