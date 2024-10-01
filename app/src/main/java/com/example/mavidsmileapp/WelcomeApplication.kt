package com.example.mavidsmileapp

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WelcomeApplication : AppCompatActivity() {

    private lateinit var nomeTextView: TextView
    private lateinit var containerOne: LinearLayout
    private lateinit var containerTwo: LinearLayout
    private lateinit var containerThree: LinearLayout
    private lateinit var nextButtonOne: ImageButton
    private lateinit var nextButtonTwo: ImageButton
    private lateinit var backButtonOne: ImageButton
    private lateinit var backButtonTwo: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome_application)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.welcome_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nomeTextView = findViewById(R.id.nomeTextView)

        containerOne = findViewById(R.id.card_container_one)
        containerTwo = findViewById(R.id.card_container_two)
        containerThree = findViewById(R.id.card_container_three)

        nextButtonOne = findViewById(R.id.next_button_card_one)
        nextButtonTwo = findViewById(R.id.next_button_card_two)

        backButtonOne = findViewById(R.id.back_button_card_two)
        backButtonTwo = findViewById(R.id.back_button_card_three)

        val nome = intent.getStringExtra("USER_NAME") ?: "Usuário" // Se não houver nome, define "Usuário"
        nomeTextView.text = "Bem Vindo $nome!"

        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        // Ao clicar no botão "nextButtonOne", exibe o segundo card
        nextButtonOne.setOnClickListener {
            showContainer(containerTwo)
        }

        // Ao clicar no botão "nextButtonTwo", exibe o terceiro card
        nextButtonTwo.setOnClickListener {
            showContainer(containerThree)
        }

        // Ao clicar no botão "backButtonOne", volta para o primeiro card
        backButtonOne.setOnClickListener {
            showContainer(containerOne)
        }

        // Ao clicar no botão "backButtonTwo", volta para o segundo card
        backButtonTwo.setOnClickListener {
            showContainer(containerTwo)
        }
    }

    // Função para mostrar um container e esconder os outros
    private fun showContainer(visibleContainer: LinearLayout) {
        containerOne.visibility = View.GONE
        containerTwo.visibility = View.GONE
        containerThree.visibility = View.GONE

        visibleContainer.visibility = View.VISIBLE
    }
}