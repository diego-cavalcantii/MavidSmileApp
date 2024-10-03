package com.example.mavidsmileapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var nomeEditText: EditText
    lateinit var buttonEntrar: Button
    lateinit var mensagemErrorTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Configuração dos paddings das insets (para barras de status, etc.)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nomeEditText = findViewById(R.id.nomeEditText)
        buttonEntrar = findViewById(R.id.buttonEntrar)
        mensagemErrorTextView = findViewById(R.id.mensagemErrorTextView)

        buttonEntrar.setOnClickListener {
            val nome = nomeEditText.text.toString()

            if(nome.isEmpty()) {
                mensagemErrorTextView.text = "Por favor, digite seu nome."
                return@setOnClickListener
            }
            if(nome.length < 3) {
                mensagemErrorTextView.text = "Por favor, digite um nome válido."
                return@setOnClickListener
            }
            nextPage(nome)
        }

    }

    fun nextPage(nome: String) {
        // Passando o nome para a próxima activity usando Intent.putExtra
        val intent = Intent(this, WelcomeApplication::class.java)
        intent.putExtra("USER_NAME", nome) // "USER_NAME" é a chave
        startActivity(intent)
    }



}
