package com.example.mavidsmileapp.services

import android.content.Context
import android.widget.Toast
import com.example.mavidsmileapp.Api
import com.example.mavidsmileapp.domains.Nivel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NivelService(private val context: Context) {

    fun fetchNiveis(onNiveisFetched: (List<Nivel>) -> Unit) {
        val service = Api.buildServiceNiveis()
        val call = service.getAllNiveis()

        call.enqueue(object : Callback<List<Nivel>> {
            override fun onResponse(call: Call<List<Nivel>>, response: Response<List<Nivel>>) {
                if (response.isSuccessful) {
                    val niveisList = response.body() ?: emptyList()
                    onNiveisFetched(niveisList)
                } else {
                    Toast.makeText(context, "Erro ao carregar níveis: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Nivel>>, t: Throwable) {
                Toast.makeText(context, "Falha na requisição: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
