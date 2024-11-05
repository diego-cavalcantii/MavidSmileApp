// Api.kt
package com.example.mavidsmileapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val URL = "http://10.0.2.2:8080"

// Cliente.kt
data class Cliente(
    val nomeNivel: String,
    val nomeCompleto: String,
    val email: String,
    val endereco: String,
    val pontos: Int,
    val premiosRecebidos: List<Premio>
)

// Modelo para `PremioResponseDTO` em Kotlin
data class Premio(
    val nome: String,
    val descricao: String
)

interface ClientePropertyService {
    @GET("/clientes/{id}")
    fun getCliente(@Path("id") id: String): Call<Cliente>
}

object Api {
    fun buildService(): ClientePropertyService {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ClientePropertyService::class.java)
    }
}
