package com.example.mavidsmileapp

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RewardsActivity : AppCompatActivity() {
    private lateinit var cameraButton: ImageButton
    private lateinit var rankingButton: ImageButton
    private lateinit var premioButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rewards)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializa os botões
        cameraButton = findViewById(R.id.cameraButton)
        rankingButton = findViewById(R.id.rankingButton)
        premioButton = findViewById(R.id.premioButton)

        // Configura a navegação com a função utilitária
        NavigationUtil.setupNavigation(this, cameraButton, rankingButton, premioButton)
    }
}
