// Api.kt
package com.example.mavidsmileapp

import com.example.mavidsmileapp.domains.Cliente
import com.example.mavidsmileapp.domains.ClienteRanking
import com.example.mavidsmileapp.domains.Nivel
import com.example.mavidsmileapp.domains.ProgressoCliente
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val URL = "http://10.0.2.2:8080"

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

interface NivelPropertyService {
    @GET("/nivel")
    fun getAllNiveis(): Call<List<Nivel>>
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

    fun buildServiceNiveis() : NivelPropertyService {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(NivelPropertyService::class.java)
    }
 }
