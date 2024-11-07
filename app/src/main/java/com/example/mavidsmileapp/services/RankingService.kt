package com.example.mavidsmileapp.services

import android.content.Context
import android.widget.Toast
import com.example.mavidsmileapp.Api
import com.example.mavidsmileapp.adapters.RankingAdapter
import com.example.mavidsmileapp.domains.ClienteRanking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RankingService(private val context: Context) {

    fun fetchRankingData(clientId: String, adapter: RankingAdapter) {
        val service = Api.buildServiceRankingCLiente()
        val call = service.getRankingCliente(clientId)

        call.enqueue(object : Callback<List<ClienteRanking>> {
            override fun onResponse(call: Call<List<ClienteRanking>>, response: Response<List<ClienteRanking>>) {
                if (response.isSuccessful) {
                    val rankingList = response.body() ?: emptyList()
                    adapter.updateData(rankingList)
                } else {
                    Toast.makeText(context, "Erro ao carregar ranking: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<ClienteRanking>>, t: Throwable) {
                Toast.makeText(context, "Falha na requisição: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
