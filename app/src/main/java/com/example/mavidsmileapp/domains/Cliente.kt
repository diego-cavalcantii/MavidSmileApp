package com.example.mavidsmileapp.domains

data class Cliente (
    val nomeNivel: String,
    val nomeCompleto: String,
    val email: String,
    val endereco: String,
    val pontos: Int,
    val imgSrc: String,
    val premiosRecebidos: List<Premio>
)