package com.example.mavidsmileapp.services

import android.content.Context
import android.widget.TextView
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.mavidsmileapp.Api
import com.example.mavidsmileapp.R
import com.example.mavidsmileapp.domains.Cliente
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClienteService(private val context: Context) {

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

                        Glide.with(binding.root.context)
                            .load(it.imgSrc)
                            .into(binding.root.findViewById(R.id.imgBio))

                        onClientFetched(it)
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
}
