package com.example.mavidsmileapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val containerOne = findViewById<LinearLayout>(R.id.containerOne)
        val containerTwo = findViewById<LinearLayout>(R.id.containerTwo)
        val nextButton = findViewById<ImageButton>(R.id.nextButton)
        val backButton = findViewById<ImageButton>(R.id.backButton)

        // Inicialmente exibe o primeiro card
        containerOne.visibility = View.VISIBLE
        containerTwo.visibility = View.GONE

        // Ao clicar no botão "setButton", troca para o segundo card
        nextButton.setOnClickListener {
            containerOne.visibility = View.GONE
            containerTwo.visibility = View.VISIBLE
        }

        // Ao clicar no botão "backButton", volta para o primeiro card
        backButton.setOnClickListener {
            containerTwo.visibility = View.GONE
            containerOne.visibility = View.VISIBLE
        }



    }
}