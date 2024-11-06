// ClienteHelper.kt
package com.example.mavidsmileapp

import android.content.Context
import android.widget.TextView
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.example.mavidsmileapp.adapters.RankingAdapter
import com.example.mavidsmileapp.domains.Cliente
import com.example.mavidsmileapp.domains.ClienteRanking
import com.example.mavidsmileapp.domains.Nivel
import com.example.mavidsmileapp.domains.ProgressoCliente
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClienteHelper(private val context: Context) {

    fun fetchClient(clientId: String, binding: ViewBinding, onClientFetched: (Cliente) -> Unit) {
        val service = Api.buildServiceCliente()
        val call = service.getCliente(clientId)

        call.enqueue(object : Callback<Cliente> {
            override fun onResponse(call: Call<Cliente>, response: Response<Cliente>) {
                if (response.isSuccessful) {
                    val cliente = response.body()
                    cliente?.let {
                        binding.root.findViewById<TextView>(R.id.nomeCliente).text = it.nomeCompleto
                        binding.root.findViewById<TextView>(R.id.nivelCliente).text = it.nomeNivel
                        binding.root.findViewById<TextView>(R.id.pontosCliente).text = "${it.pontos}pts"
                        onClientFetched(it) // Passa o cliente para o callback
                    }
                } else {
                    Toast.makeText(context, "Erro ao buscar cliente: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Cliente>, t: Throwable) {
                Toast.makeText(context, "Falha na requisição: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun fetchProgressoCliente(clienteId: String, binding: ViewBinding) {
        val service = Api.buildServiceProgressoCliente()
        val call = service.getProgressoCliente(clienteId)

        call.enqueue(object : Callback<ProgressoCliente> {
            override fun onResponse(call: Call<ProgressoCliente>, response: Response<ProgressoCliente>) {
                if (response.isSuccessful) {
                    val progressoCliente = response.body()
                    progressoCliente?.let {
                        binding.root.findViewById<TextView>(R.id.registrosCliente).text =
                            "${it.registros} ${if (it.registros > 1) "registros" else "registro"}"
                    }
                }
            }

            override fun onFailure(call: Call<ProgressoCliente>, t: Throwable) {
                Toast.makeText(context, "Falha na requisição: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun fetchRankingData(clientId: String, adapter: RankingAdapter) {
        val service = Api.buildServiceRankingCLiente()
        val call = service.getRankingCliente(clientId)

        call.enqueue(object : Callback<List<ClienteRanking>> {
            override fun onResponse(call: Call<List<ClienteRanking>>, response: Response<List<ClienteRanking>>) {
                if (response.isSuccessful) {
                    val rankingList = response.body() ?: emptyList()
                    println("Ranking data size: ${rankingList.size}")
                    adapter.updateData(rankingList)
                } else {
                    println("Erro ao carregar ranking: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<ClienteRanking>>, t: Throwable) {
                Toast.makeText(context, "Falha na requisição: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun fetchNiveis(onNiveisFetched: (List<Nivel>) -> Unit) {
        val service = Api.buildServiceNiveis()
        val call = service.getAllNiveis()

        call.enqueue(object : Callback<List<Nivel>> {
            override fun onResponse(call: Call<List<Nivel>>, response: Response<List<Nivel>>) {
                if (response.isSuccessful) {
                    val niveisList = response.body() ?: emptyList()
                    onNiveisFetched(niveisList) // Passa todos os níveis via callback
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
