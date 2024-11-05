// ClienteHelper.kt
package com.example.mavidsmileapp

import android.content.Context
import android.widget.TextView
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClienteHelper(private val context: Context) {

    fun fetchClient(clientId: String, binding: ViewBinding) {
        val service = Api.buildServiceCliente()
        val call = service.getCliente(clientId)

        call.enqueue(object : Callback<Cliente> {
            override fun onResponse(call: Call<Cliente>, response: Response<Cliente>) {
                if (response.isSuccessful) {
                    val cliente = response.body()
                    cliente?.let {
                        // Acesse o TextView do layoutBio dinamicamente
                        binding.root.findViewById<TextView>(R.id.nomeCliente).text = it.nomeCompleto
                        binding.root.findViewById<TextView>(R.id.nivelCliente).text = it.nomeNivel
                        binding.root.findViewById<TextView>(R.id.pontosCliente).text = "${it.pontos}pts"

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

    fun fetchProgressoCliente(clienteId: String, binding: ViewBinding){
        val service = Api.buildServiceProgressoCliente()
        val call = service.getProgressoCliente(clienteId)

        call.enqueue(object : Callback<ProgressoCliente>{
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


}
