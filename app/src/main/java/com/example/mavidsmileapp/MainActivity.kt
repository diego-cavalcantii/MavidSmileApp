package com.example.mavidsmileapp

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var cameraButton: ImageButton
    private lateinit var cardEscovaEFioDental: CardView
    private lateinit var rankingButton: ImageButton
    private lateinit var premioButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ajustar padding com base nos insets do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar os botões
        cameraButton = findViewById(R.id.cameraButton)
        cardEscovaEFioDental = findViewById(R.id.cardEscovaEFioDental)
        rankingButton = findViewById(R.id.rankingButton)
        premioButton = findViewById(R.id.premioButton)

        // Lógica do card visível/invisível
        cardEscovaEFioDental.visibility = View.GONE
        cameraButton.setOnClickListener {
            cardEscovaEFioDental.visibility =
                if (cardEscovaEFioDental.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
    }
}
