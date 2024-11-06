// Api.kt
package com.example.mavidsmileapp

import com.google.gson.Gson
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
data class Premio(
    val nomePremio: String,
    val descricaoPremio:String,
)

data class ClienteRanking(
    val nomeCompleto: String,
    val nomeNivel: String,
    val pontos: Int
)

data class ProgressoCliente(
    val nomeCompleto: String,
    val registros: Int,
    val pontos: Int,
)

interface ClientePropertyService {
    @GET("/clientes/{id}")
    fun getCliente(@Path("id") id: String): Call<Cliente>
}

interface ProgressoPropertyService {
    @GET("progresso/{id}")
    fun getProgressoCliente(@Path("id") id: String): Call<ProgressoCliente>
}
interface RankingPropertyService {
    @GET("amizade/ranking/{id}")
    fun getRankingCliente(@Path("id") id: String): Call<List<ClienteRanking>>
}

interface PremiosPropertyService {
    @GET("/premios")
    fun getAllPremios(): Call<List<Premio>>
}

object Api {
    fun buildServiceCliente(): ClientePropertyService {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ClientePropertyService::class.java)
    }

    fun buildServiceProgressoCliente() : ProgressoPropertyService {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ProgressoPropertyService::class.java)
    }

    fun buildServiceRankingCLiente() : RankingPropertyService {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RankingPropertyService::class.java)
    }

    fun buildServicePremios() : PremiosPropertyService {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(PremiosPropertyService::class.java)
    }
 }
